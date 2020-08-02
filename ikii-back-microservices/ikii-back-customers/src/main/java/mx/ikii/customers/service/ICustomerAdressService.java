package mx.ikii.customers.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.GeoResult;

import mx.ikii.commons.persistence.collection.CustomerAdress;

public interface ICustomerAdressService {

	Page<CustomerAdress> getAll(Pageable pageable);
	
	CustomerAdress getById(String customerAdressId);
	
	List<CustomerAdress> getByCustomerId(String customerId);
	
	CustomerAdress createCustomerAddress(CustomerAdress customerAdress);
	
	void deleteCustomerAddress(String customerAdressId);
	
	CustomerAdress updateCustomerAddress(CustomerAdress customerAdress, String id);
	
	List<GeoResult<CustomerAdress>> findByLocationNear(String latitude, String longitude, Double maxDistance);
	
	List<CustomerAdress> nearByMe(Double latitude, Double longitude, Double maxDistance);
	
}
