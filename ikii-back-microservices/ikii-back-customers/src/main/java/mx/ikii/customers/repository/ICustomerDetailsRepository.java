package mx.ikii.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.ikii.commons.persistence.collection.CustomerDetails;

/**
 * This interface interacts directly with the CustomerDetails Collection of the mongodb
 * database
 * 
 * @author Francisco Javier Martínez Arazo
 *
 */
public interface ICustomerDetailsRepository extends MongoRepository<CustomerDetails, String>{

}
