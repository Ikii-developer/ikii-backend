package mx.ikii.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mx.ikii.commons.persistence.collection.CustomerDetails;

public interface ICustomerDetailsRepository extends MongoRepository<CustomerDetails, String>{

}
