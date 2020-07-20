package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.BusinessCategory;

public interface IBusinessCategoryService {

	BusinessCategory findById(String id);

	BusinessCategory findByUserName(String userName);

	Page<BusinessCategory> findAll(Pageable pageable);

	BusinessCategory create(BusinessCategory transaction);

	BusinessCategory update(BusinessCategory transaction, String id);

	void delete(String id);

}
