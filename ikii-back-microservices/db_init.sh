#!/bin/bash
																																																																								MONGO_PORT=$1

# MONGO RESTORE
cd dbinit && mongorestore --host 127.0.0.1 --port $MONGO_PORT --db ikiidb -u "ikii@dm1ndB" -p "pass1Ki1Db" --authenticationDatabase "admin"

echo "Admin users created."

