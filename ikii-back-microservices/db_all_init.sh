#!/bin/bash

#The order of the scripts executions is important	
																			
# Execute users restore
sh ./db_users_init.sh

# Execute business restore
sh ./db_business_init.sh

# Execute business-category restore
sh ./db_business_category_init.sh

# Execute business-rate restore
sh ./db_business_rate_init.sh

# Execute categories restore
sh ./db_categories_init.sh

# Execute customer-address restore
sh ./db_customer_address.sh


echo "All init db executed."

