package mx.ikii.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Privilege;

@Repository
public interface IUserPrivilegeRepository extends MongoRepository<Privilege, String> {

}
