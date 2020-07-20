package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.business.BusinessCategoryRequest;
import mx.ikii.commons.payload.response.business.BusinessCategoryResponse;

public interface IBusinessCategoryServiceWrapper {

	BusinessCategoryResponse findById(String id);

	BusinessCategoryResponse getByBusinesName(String name);

	Page<BusinessCategoryResponse> findAll(Pageable pageable);

	BusinessCategoryResponse create(BusinessCategoryRequest businessCategoryRequest);

	BusinessCategoryResponse update(BusinessCategoryRequest businessCategoryRequest, String id);

	void delete(String id);

}
