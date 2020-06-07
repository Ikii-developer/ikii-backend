#!/bin/bash

DOCKER_SERVICE=$1

docker container rm -f $DOCKER_SERVICE
# COMPILE COMMONS MODULE
cd .. && cd ikii-back-commons && mvn clean install

# COMPILE MICROSERVICE
cd .. && cd ikii-back-microservices/$DOCKER_SERVICE && mvn clean install -DskipTests -P development

#START RUNNING
cd .. && docker-compose up --build -d $DOCKER_SERVICE

echo "Container "${DOCKER_SERVICE}" is now online."

