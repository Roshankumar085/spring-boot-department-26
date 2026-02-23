# Multi-stage Dockerfile: build with Maven, run with OpenJDK 17

# --- Build stage ---
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /workspace
# copy pom and download dependencies first (leverage docker cache)
COPY pom.xml .
RUN mvn -B -ntp -f pom.xml -e -DskipTests dependency:go-offline

# copy source and package
COPY src ./src
RUN mvn -B -ntp -f pom.xml package -DskipTests

# --- Run stage ---
FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp
# Use a non-root user for security
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
WORKDIR /app
# Copy wait script and give execution permission
COPY wait-for.sh /wait-for.sh
RUN chmod +x /wait-for.sh
# Install netcat (nc) to allow checking TCP port availability
RUN apk add --no-cache netcat-openbsd

COPY --from=build /workspace/target/app-0.0.1-SNAPSHOT.jar app.jar
RUN chown appuser:appgroup /app/app.jar
USER appuser
EXPOSE 8080
# Use wait-for to delay start until db is available. Docker Compose sets env vars for DB host/port.
ENTRYPOINT ["/wait-for.sh","db","3306","java","-jar","/app/app.jar"]
