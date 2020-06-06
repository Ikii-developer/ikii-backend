#!/bin/bash
docker-compose down --rmi all --remove-orphans -v
docker system prune -a -f

echo "All docker data was removed from the system."

