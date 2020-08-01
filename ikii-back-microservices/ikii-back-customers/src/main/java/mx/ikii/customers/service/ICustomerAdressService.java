package mx.ikii.customers.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressService {

	Page<CustomerAdress> getAll(Pageable pageable);
	
	CustomerAdress getById(String customerAdressId);
	
	List<CustomerAdress> getByCustomerId(String customerId);
	
	CustomerAdress createCustomerAddress(CustomerAdress customerAdress);
	
	void deleteCustomerAddress(String customerAdressId);
	
	CustomerAdress updateCustomerAddress(CustomerAdress customerAdress, String id);
	
	List<CustomerAdress> nearByMe(String latitude, String longitude, Double maxDistance);
	
	List<CustomerAdress> nearByMe2(String latitude, String longitude, Double maxDistance);
	
	List<CustomerAdress> nearByMe3(String latitude, String longitude, Double maxDistance);
}
