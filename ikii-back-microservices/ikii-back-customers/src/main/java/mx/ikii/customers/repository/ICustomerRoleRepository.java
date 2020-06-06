package mx.ikii.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Role;

@Repository
public interface ICustomerRoleRepository extends MongoRepository<Role, String> {

}
