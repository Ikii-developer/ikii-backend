package mx.ikii.customers.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.Customer;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface ICustomerService {

	Customer  findById(String id);

	Customer  findByEmail(String userName);

	Page<Customer> findAll(Pageable pageable);

	Customer signUp(Customer customer);

	Customer update(Customer customer, String id);

	void delete(String id);
	
	Customer findByPhoneNumber(String id);

}
