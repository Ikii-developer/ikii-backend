#!/bin/bash
docker-compose -f docker-compose.yml  kill 

# COMPILE MICROSERVICES
cd .. && cd ikii-back-microservices && mvn clean install -DskipTests -P production

#START RUNNING
docker-compose up --build -d

echo "All containers are now online."

