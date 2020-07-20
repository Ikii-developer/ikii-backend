package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;

public interface IBusinessCategoryServiceWrapper {

	BusinessCategoryResponse findById(String id);

	BusinessCategoryResponse getByBusinesName(String businessName);

	Page<BusinessCategoryResponse> findAll(Pageable pageable);

	BusinessCategoryResponse create(BusinessCategoryRequest transaction);

	BusinessCategoryResponse update(BusinessCategoryRequest transaction, String id);

	void delete(String id);

}
