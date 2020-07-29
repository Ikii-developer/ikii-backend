#!/bin/bash

#The order of the scripts executions is important	
																			
# Execute users restore
sh ./db_users_init.sh

# Execute categories restore
sh ./db_categories_init.sh

# Execute business restore
sh ./db_business_init.sh


echo "All init db executed."

