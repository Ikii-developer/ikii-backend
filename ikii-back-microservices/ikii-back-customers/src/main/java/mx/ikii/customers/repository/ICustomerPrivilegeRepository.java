package mx.ikii.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Privilege;

@Repository
public interface ICustomerPrivilegeRepository extends MongoRepository<Privilege, String> {

}
