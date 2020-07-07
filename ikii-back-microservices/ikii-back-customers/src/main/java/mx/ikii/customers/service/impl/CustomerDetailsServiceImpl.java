package mx.ikii.customers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.customers.repository.ICustomerDetailsRepository;
import mx.ikii.customers.service.ICustomerDetailsService;

@Service
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

	@Autowired
	private ICustomerDetailsRepository customerDetailsRepository;

	@Override
	public CustomerDetails getById(String customerDetailsId) {
		return customerDetailsRepository.findById(customerDetailsId)
				.orElseThrow(() -> new ResourceNotFoundException(customerDetailsId));

	}

	@Override
	public CustomerDetails getByCustomerId(String customerId) {
		return customerDetailsRepository.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(customerId));

	}

	@Override
	public List<CustomerDetails> getAll(Pageable pageable) {
		return customerDetailsRepository.findAll(pageable).getContent();
	}

	@Override
	public CustomerDetails create(CustomerDetails customerDetails) {
		return customerDetailsRepository.save(customerDetails);
	}

	@Override
	public CustomerDetails update(CustomerDetails customerDetails, String id) {
		return null;
	}

	@Override
	public void delete(String customerDetailsId) {
		customerDetailsRepository.deleteById(customerDetailsId);
	}

}
