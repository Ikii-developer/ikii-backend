#!/bin/bash
																																																																								MONGO_PORT=$1

# MONGO RESTORE
cd dbinit && mongorestore --host 127.0.0.1 --port $MONGO_PORT --db ikiidb --username=ikii@dm1ndB --password=pass1Ki1Db --authenticationDatabase=ikiidb

echo "Admin users created."

