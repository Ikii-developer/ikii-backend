package mx.ikii.customers.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.exception.handler.helper.ConflictException;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.utils.constants.EnumError;
import mx.ikii.customers.helper.Helper;
import mx.ikii.customers.repository.ICustomerRepository;
import mx.ikii.customers.service.ICustomerService;

/**
 * This class contains the CRUD operations related to the user resource
 * 
 */

@Service
@Slf4j
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer signUp(Customer customer) {
		Optional<Customer> emailCustomer = customerRepository.findByEmail(customer.getEmail());
		if (emailCustomer.isPresent()) {
			throw new ConflictException(EnumError.EMAIL_ALREADY_EXIST);
		}

		Optional<Customer> phoneCustomer = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
		if (phoneCustomer.isPresent()) {
			throw new ConflictException(EnumError.PHONE_ALREADY_EXIST);
		}

		customer.setIsEnabled(true);
		Customer customerResponse = customerRepository.save(customer);
		return customerResponse;
	}

	@Override
	public Customer findById(String id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Customer.class));
		return customer;
	}

	@Override
	public Customer findByEmail(String email) {
		Optional<Customer> customer = customerRepository.findByEmail(email);
		return customer.orElseThrow(() -> new ResourceNotFoundException(email, Customer.class));
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
		return customer.orElseThrow(() -> new ResourceNotFoundException(phoneNumber, Customer.class));
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer update(Customer newCustomer, String id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, Customer.class));
		Helper.setUpdateProperties(customer, newCustomer);
		customer = customerRepository.save(customer);
		return customer;
	}

	@Override
	public void delete(String id) {
		customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Customer.class));
		customerRepository.deleteById(id);
	}
}
