#!/usr/bin/env bash

mvn clean package
docker build -t shipment-monolith:latest .
docker run -p 8080:8080 -t shipment-monolith:latest