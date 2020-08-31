#!/bin/bash

backup_folder=$1

echo "**************** Execute mongodump to create a backup ****************"

mongodump --host 127.0.0.1 --port 27020 --db ikiidb -u "ik1iU53r<3" -p "iki1P@5s<3" --authenticationDatabase "ikiidb" --out $backup_folder

echo "Ikii back-up created"

