package mx.ikii.customers.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.CustomerAdress;

/**
 * This interface interacts directly with the CustomerAddress Collection of the mongodb
 * database
 * 
 * @author Francisco Javier Martínez Arazo
 *
 */
@Repository
public interface ICustomerAdressRepository extends MongoRepository<CustomerAdress, String> {

	List<CustomerAdress> findByCustomerId(String customerId);
	Optional<CustomerAdress> findCurrentByCustomerIdAndIsCurrent(String customerId);
	CustomerAdress findByIsCurrent();
	
	GeoResults<CustomerAdress> findByLocationNear(Point location, Distance distance);
	
}
