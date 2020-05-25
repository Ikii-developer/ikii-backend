package mx.ikii.business.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;

public interface IBusinessServiceWrapper {

	BusinessResponse findById(String id);

	BusinessResponse findByUseName(String userName);

	Page<BusinessResponse> findAll(Pageable pageable);

	BusinessResponse create(BusinessRequest transaction);

	BusinessResponse update(BusinessRequest transaction, String id);

	void delete(String id);

	
}
