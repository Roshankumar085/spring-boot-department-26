# spring-boot-application

This project is a Spring Boot application that manages departments. It is configured to use MySQL as its datasource.

## Local build (Maven)

Build the project with the included Maven wrapper:

```bash
./mvnw -DskipTests package
```

Or with Windows PowerShell:

```powershell
mvnw.cmd -DskipTests package
```

## Docker

Build and run the app with Docker Compose (recommended):

```bash
docker-compose up --build
```

This starts a MySQL 8.0 container and the Spring Boot app wired to it. The app will be available on http://localhost:8080

If you prefer to build the image manually:

```bash
# build
docker build -t spring-department-app:latest .
# run (example, pointing to a local MySQL)
docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/department_db -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=yourpw -p 8080:8080 spring-department-app:latest
```

## Git

To push this repository to your GitHub remote:

```bash
# add remote (only once)
git remote add origin https://github.com/Roshankumar085/spring-boot-department-26.git

# push main branch (replace main with your branch name)
git push -u origin main
```

If you need to authenticate, use a personal access token or SSH key as appropriate.

