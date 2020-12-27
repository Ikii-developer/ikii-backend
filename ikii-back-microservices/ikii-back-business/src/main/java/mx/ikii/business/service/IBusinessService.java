package mx.ikii.business.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.Business;

public interface IBusinessService {

	Business findById(String id);

	Business findByName(String name);
	
	List<Business> filterByBusinessName(String name);

	Page<Business> findAll(Pageable pageable);

	Business create(Business business);

	Business update(Business business, String id);

	void delete(String id);

	Business findByCustomerId(String customerId);

}
