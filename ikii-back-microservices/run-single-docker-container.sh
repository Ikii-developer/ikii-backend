#!/bin/bash

DOCKER_SERVICE=$1

# COMPILE COMMONS MODULE
cd .. && cd ikii-back-commons && mvn clean install

# COMPILE MICROSERVICES
cd .. && cd ikii-back-microservices && mvn clean install -DskipTests -P development

#START RUNNING
docker-compose up -d $DOCKER_SERVICE

echo "Container "+${DOCKER_SERVICE}+" is now online."

