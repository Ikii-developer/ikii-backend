package mx.ikii.business.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Business;

@Repository
public interface IBusinessRepository extends MongoRepository<Business, String> {
	
	Business findByName(String name);

}
