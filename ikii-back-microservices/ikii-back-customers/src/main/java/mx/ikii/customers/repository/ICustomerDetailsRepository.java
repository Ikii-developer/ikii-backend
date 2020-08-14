package mx.ikii.customers.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.CustomerDetails;

/**
 * This interface interacts directly with the CustomerDetails Collection of the
 * mongodb database
 * 
 * @author Francisco Javier Martínez Arazo
 *
 */
@Repository
public interface ICustomerDetailsRepository extends MongoRepository<CustomerDetails, String> {
	public Optional<CustomerDetails> findByCustomerId(ObjectId customerId);
}
