package mx.ikii.users.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.payload.response.user.CustomerResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface ICustomerServiceWrapper {

	CustomerResponse findById(String id);

	CustomerResponse findByemail(String email);

	Page<CustomerResponse> findAll(Pageable pageable);

	CustomerResponse signUp(CustomerRequest transaction);

	CustomerResponse update(CustomerRequest transaction, String id);
	
	CustomerResponse findByTelephone(String id);

	void delete(String id);

	Role create(Role role);

	Privilege create(Privilege privilege);

	void assignPrivilege(String roleId, String privilegeId);

	void assignRole(String userId, String roleId);

}
