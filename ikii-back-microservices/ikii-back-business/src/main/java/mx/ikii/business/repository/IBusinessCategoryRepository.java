package mx.ikii.business.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.BusinessCategory;

@Repository
public interface IBusinessCategoryRepository extends MongoRepository<BusinessCategory, String> {

	BusinessCategory findByName(String name);

}
