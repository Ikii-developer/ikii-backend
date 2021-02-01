#!/bin/bash

DOCKER_SERVICE=$1

docker container rm -f $DOCKER_SERVICE

# COMPILE MICROSERVICE
cd $DOCKER_SERVICE && mvn clean install -DskipTests -P production

#START RUNNING
cd .. && docker-compose up --build -d $DOCKER_SERVICE

echo "Container "${DOCKER_SERVICE}" is now online."

