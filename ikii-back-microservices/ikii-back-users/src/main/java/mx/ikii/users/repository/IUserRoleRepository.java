package mx.ikii.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Role;

@Repository
public interface IUserRoleRepository extends MongoRepository<Role, String> {

}
