#!/bin/sh
set -e

if [ "$#" -lt 2 ]; then
  echo "Usage: $0 host port -- command args..."
  exit 1
fi

HOST=$1
PORT=$2
shift 2

echo "Waiting for $HOST:$PORT..."

# Wait until the port is open
while ! nc -z "$HOST" "$PORT"; do
  echo "Waiting for $HOST:$PORT..."
  sleep 1
done

echo "$HOST:$PORT is available — executing command: $@"
exec "$@"

