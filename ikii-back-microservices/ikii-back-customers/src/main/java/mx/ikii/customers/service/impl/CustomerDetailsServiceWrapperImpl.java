package mx.ikii.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.customer.ICustomerDetailsMapper;
import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.service.ICustomerDetailsService;
import mx.ikii.customers.service.ICustomerDetailsServiceWrapper;

@Service
public class CustomerDetailsServiceWrapperImpl implements ICustomerDetailsServiceWrapper {
	
	@Autowired
	private ICustomerDetailsService customerDetailsService;
	
	@Autowired
	private ICustomerDetailsMapper customerDetailsMapper;

	@Override
	public CustomerDetailsResponse getById(String id) {
		return customerDetailsMapper.entityToResponse(customerDetailsService.getById(id));
	}

	@Override
	public CustomerDetailsResponse getByCustomerId(String customerId) {
		return customerDetailsMapper.entityToResponse(customerDetailsService.getByCustomerId(customerId));
	}

	@Override
	public Page<CustomerDetailsResponse> getAll(Pageable pageable) {
		List<CustomerDetailsResponse> response = customerDetailsMapper.entityToResponse(customerDetailsService.getAll(pageable));
		return PageHelper.createPage(response, pageable, new Long(response.size()));
	}

	@Override
	public CustomerDetailsResponse create(CustomerDetailsRequest request) {
		CustomerDetails customerDetails = customerDetailsMapper.requestToEntity(request);
		return customerDetailsMapper.entityToResponse(customerDetailsService.create(customerDetails));
	}

	@Override
	public CustomerDetailsResponse update(CustomerDetailsRequest request, String id) {
		CustomerDetails customerDetails = customerDetailsMapper.requestToEntity(request);
		return customerDetailsMapper.entityToResponse(customerDetailsService.update(customerDetails, id));
	}

	@Override
	public void delete(String id) {
		customerDetailsService.delete(id);
	}
	
}
