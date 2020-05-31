package mx.ikii.users.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.helper.ConflictException;
import mx.ikii.commons.exception.handler.helper.RepositoryHelper;
import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.constants.EnumError;
import mx.ikii.users.helper.Helper;
import mx.ikii.users.repository.ICustomerRepository;
import mx.ikii.users.service.ICustomerService;

/**
 * This class contains the CRUD operations related to the user resource
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public Customer signUp(Customer customer) {
		
		if (Nullable.isNull(customer)) {
			return null;
		}
		
		Optional<Customer> customerOptional = customerRepository.findByEmail(customer.getEmail());

		if (customerOptional.isPresent()) {
			throw new ConflictException(EnumError.EMAIL_ALREADY_EXIST);
		}

		customerOptional = customerRepository.findByPhoneNumber(customer.getPhoneNumber());
		
		if (customerOptional.isPresent()) {
			throw new ConflictException(EnumError.PHONE_ALREADY_EXIST);
		}

		Customer customerResponse = customerRepository.save(customer);
		
		return customerResponse;
	}

	@Override
	public Customer findById(String id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return RepositoryHelper.validateOptional(customer, id, Customer.class);
	}

	@Override
	public Customer findByEmail(String email) {
		Optional<Customer> customer = customerRepository.findByEmail(email);
		return RepositoryHelper.validateOptional(customer, email, Customer.class);
	}

	@Override
	public Customer findByPhoneNumber(String phoneNumber) {
		Optional<Customer> customer = customerRepository.findByPhoneNumber(phoneNumber);
		return RepositoryHelper.validateOptional(customer, phoneNumber, Customer.class);
	}

	@Override
	public Page<Customer> findAll(Pageable pageable) {
		if (Nullable.isNullOrEmpty(pageable)) {
			return null;
		}
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer update(Customer newCustomer, String id) {
		if(StringUtils.isBlank(id) || newCustomer == null) {
			return null;
		}
		
		Customer customer = customerRepository.findById(id).orElse(null);
		RepositoryHelper.validateOptional(Optional.ofNullable(customer), id, Customer.class);

		Helper.setUpdateProperties(customer, newCustomer);
		customer = customerRepository.save(customer);
		
		return customer;
	}

	@Override
	public void delete(String id) {
		Customer customer = customerRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(customer), id, Customer.class);
		customerRepository.deleteById(id);
	}

}
