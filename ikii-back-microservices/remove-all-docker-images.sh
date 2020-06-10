#!/bin/bash

docker rmi -f $(docker images -a -q)

echo "All images removed from tge system."

