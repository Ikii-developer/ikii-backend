#!/bin/bash
																																																																								MONGO_PORT=$1

# MONGO RESTORE
cd dbinit/products && mongorestore --host 127.0.0.1 --port 27020 --db ikiidb -u "ik1iU53r<3" -p "iki1P@5s<3" --authenticationDatabase "ikiidb"

echo "Product models created."

