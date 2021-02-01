package mx.ikii.business.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.persistence.collection.JoinUs;

@Repository
public interface IHelpBusinessRepository extends MongoRepository<JoinUs, String>{

}
