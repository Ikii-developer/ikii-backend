package mx.ikii.business.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.BusinessRate;

@Repository
public interface IBusinessRateRepository extends MongoRepository<BusinessRate, String>{
	
	Optional<BusinessRate> findByBusinessId(String businessId);

}
