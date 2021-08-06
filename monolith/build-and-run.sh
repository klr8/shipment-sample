#!/usr/bin/env bash

echo "-----------------------------------------------------------"
echo "Building and running monolith"
echo "Make sure you've got the following prerequisites installed:"
echo "  - Java 11"
echo "  - Maven 3.x"
echo "  - Docker"
echo "-----------------------------------------------------------"

mvn clean package
docker build -t shipment-monolith:latest .
docker run -p 8080:8080 -t shipment-monolith:latest