#!/bin/bash
docker-compose down -v
docker system prune -a -f

echo "All docker data was removed from the system."

