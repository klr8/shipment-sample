#!/usr/bin/env bash

cd warehouse
mvn clean package
docker build -t warehouse:latest .
docker run -p 8081:8080 -t warehouse:latest &
cd ..

cd customer-service
mvn clean package
docker build -t customer-service:latest .
docker run -p 8082:8080 -t customer-service:latest &
cd ..

cd shipment
mvn clean package
docker build -t shipment:latest .

# Not running from inside a Docker container because it becomes a hassle to acess the other Docker
# containers running on the host
# docker run -p 8080:8080 -t shipment:latest
mvn spring-boot:run