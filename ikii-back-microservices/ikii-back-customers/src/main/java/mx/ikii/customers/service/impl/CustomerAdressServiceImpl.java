package mx.ikii.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.mapper.customer.ICustomerAdressMapper;
import mx.ikii.commons.persistence.collection.CustomerAdress;
import mx.ikii.customers.repository.ICustomerAdressRepository;
import mx.ikii.customers.service.ICustomerAdressService;

@Service
public class CustomerAdressServiceImpl implements ICustomerAdressService {

	@Autowired
	private ICustomerAdressRepository customerAdressRepository;
	
	@Autowired
	private ICustomerAdressMapper customerAdressMapper;

	@Override
	public Page<CustomerAdress> getAll(Pageable pageable) {
		return customerAdressRepository.findAll(pageable);
	}
	
	@Override
	public CustomerAdress getById(String id) {
		return customerAdressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, CustomerAdress.class));
	}

	@Override
	public List<CustomerAdress> getByCustomerId(String customerId) {
		return customerAdressRepository.findByCustomerId(customerId);
	}

	@Override
	public CustomerAdress createCustomerAddress(CustomerAdress request) {
		return customerAdressRepository.insert(request);
	}

	@Override
	public CustomerAdress updateCustomerAddress(CustomerAdress request, String id) {
		CustomerAdress customerAdress = customerAdressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id,CustomerAdress.class));
		customerAdressMapper.updateEntity(customerAdress, request);
		return customerAdressRepository.save(customerAdress);
	}

	@Override
	public void deleteCustomerAddress(String id) {
		customerAdressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, CustomerAdress.class));
		customerAdressRepository.deleteById(id);
	}

}
