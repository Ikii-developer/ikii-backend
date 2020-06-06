package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.Business;

public interface IBusinessService {

	Business findById(String id);

	Business findByUserName(String userName);

	Page<Business> findAll(Pageable pageable);

	Business create(Business transaction);

	Business update(Business transaction, String id);

	void delete(String id);

}
