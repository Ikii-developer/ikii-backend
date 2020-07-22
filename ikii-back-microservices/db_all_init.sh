#!/bin/bash
																			
# Execute users restore
sh ./db_users_init.sh

# Execute categories restore
sh ./db_categories_init.sh

echo "All init db executed."

