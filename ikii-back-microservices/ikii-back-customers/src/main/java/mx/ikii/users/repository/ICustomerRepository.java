package mx.ikii.users.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Customer;

/**
 * This interface interacts directly with the UserClip Collection of the mongodb
 * database
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
@Repository
public interface ICustomerRepository extends MongoRepository<Customer, String> {
	
    @Query("{'email': {$regex: ?0 }})")
	Optional<Customer> findByEmail(String email);
	Optional<Customer> findByPhoneNumber(String phoneNumber);
}
