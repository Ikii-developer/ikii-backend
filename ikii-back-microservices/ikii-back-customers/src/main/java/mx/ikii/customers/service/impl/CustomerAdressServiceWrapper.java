package mx.ikii.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.customer.ICustomerAdressMapper;
import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.commons.persistence.collection.CustomerAdress;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.service.ICustomerAdressService;
import mx.ikii.customers.service.ICustomerAdressServiceWrapper;

@Service
public class CustomerAdressServiceWrapper implements ICustomerAdressServiceWrapper {

	@Autowired
	private ICustomerAdressService customerAdressService;
	
	@Autowired
	private ICustomerAdressMapper customerAdressMapper;

	@Override
	public Page<CustomerAdressResponse> getAll(Pageable pageable) {
		Page<CustomerAdress> customerAddress = customerAdressService.getAll(pageable);
		List<CustomerAdressResponse> usersResponse = customerAdressMapper.entityToResponse(customerAddress.getContent());
		return PageHelper.createPage(usersResponse, pageable, customerAddress.getTotalElements());
	}
	
	@Override
	public CustomerAdressResponse getById(String customerAddressId) {
		return customerAdressMapper.entityToResponse(customerAdressService.getById(customerAddressId));
	}

	@Override
	public Page<CustomerAdressResponse> getByCustomerId(String customerId, Pageable pageable) {
		List<CustomerAdress> customerAdress = customerAdressService.getByCustomerId(customerId);
		List<CustomerAdressResponse> response = customerAdressMapper.entityToResponse(customerAdress);
		return PageHelper.createPage(response, pageable, new Long(customerAdress.size()));
	}

	@Override
	public CustomerAdressResponse create(CustomerAdressRequest request) {
		CustomerAdress customerAdress = customerAdressMapper.requestToEntity(request);
		return customerAdressMapper.entityToResponse(customerAdressService.createCustomerAddress(customerAdress));
	}

	@Override
	public CustomerAdressResponse update(CustomerAdressRequest request, String id) {
		CustomerAdress customerAdress = customerAdressMapper.requestToEntity(request);
		return customerAdressMapper.entityToResponse(customerAdressService.updateCustomerAddress(customerAdress,id));
	}

	@Override
	public void delete(String customerAddressId) {
		customerAdressService.deleteCustomerAddress(customerAddressId);
	}

}