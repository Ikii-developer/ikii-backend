#!/bin/bash

#The order of the scripts executions is important	
																			
echo "**************** Execute users restore ****************"
sh ./db_users_init.sh


echo "**************** Execute business_categories restore ****************"
sh ./db_business_categories_init.sh


echo "**************** Execute business restore ****************"
sh ./db_business_init.sh


echo "**************** Execute business-rate restore ****************"
sh ./db_business_rate_init.sh


echo "**************** Execute customer-details restore ****************"
sh ./db_customer_details_init.sh


echo "**************** Execute customer-address restore ****************"
sh ./db_customer_address.sh


echo "All init db executed."

