package mx.ikii.users.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.user.ICustomerMapper;
import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.payload.response.user.CustomerResponse;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.users.repository.ICustomerPrivilegeRepository;
import mx.ikii.users.repository.ICustomerRoleRepository;
import mx.ikii.users.service.ICustomerService;
import mx.ikii.users.service.ICustomerServiceWrapper;

/**
 * 
 * This class acts as a wrapper layer to the business logic in the UserClip
 * service
 * 
 */
@Service
public class CustomerServiceWrapperImpl implements ICustomerServiceWrapper {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ICustomerMapper customerMapper;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private ICustomerPrivilegeRepository customerPrivilegeRepository;

	@Autowired
	private ICustomerRoleRepository customerRoleRepository;

	@Override
	public CustomerResponse findById(String id) {
		return customerMapper.entityToResponse(customerService.findById(id));
	}

	@Override
	public CustomerResponse findByemail(String email) {
		return customerMapper.entityToResponse(customerService.findByEmail(email));
	}

	@Override
	public Page<CustomerResponse> findAll(Pageable pageable) {
		Page<Customer> customerClip = customerService.findAll(pageable);
		List<CustomerResponse> usersResponse = customerMapper.entityToResponse(customerClip.getContent());
		return PageHelper.createPage(usersResponse, pageable, customerClip.getTotalElements());
	}

	@Override
	public CustomerResponse signUp(CustomerRequest customerRequest) {
		Customer customer = customerMapper.requestToEntity(customerRequest);
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		return customerMapper.entityToResponse(customerService.signUp(customer));
	}

	@Override
	public CustomerResponse update(CustomerRequest customerRequest, String id) {
		Customer customerEntity = customerMapper.requestToEntity(customerRequest);
		return customerMapper.entityToResponse(customerService.update(customerEntity, id));
	}

	@Override
	public void delete(String id) {
		customerService.delete(id);
	}

	@Override
	public CustomerResponse findByPhoneNumber(String phoneNumber) {
		return customerMapper.entityToResponse(customerService.findByPhoneNumber(phoneNumber));
	}

	@Override
	public Role create(Role role) {
		return customerRoleRepository.save(role);
	}

	@Override
	public Privilege create(Privilege privilege) {
		return customerPrivilegeRepository.save(privilege);
	}

	@Override
	public void assignPrivilege(String roleId, String privilegeId) {
		Optional<Role> customerRole = customerRoleRepository.findById(roleId);
		if (customerRole.isPresent()) {
			Optional<Privilege> userPrivilege = customerPrivilegeRepository.findById(privilegeId);
			if (userPrivilege.isPresent()) {
				Role roleToUpdate = customerRole.get();
				roleToUpdate.getPrivileges().add(userPrivilege.get());
				customerRoleRepository.save(roleToUpdate);
			}
		}
	}

	@Override
	public void assignRole(String userId, String roleId) {
		Customer customer = customerService.findById(userId);
		Optional<Role> customerRole = customerRoleRepository.findById(roleId);
		if (customerRole.isPresent()) {
			customer.getRoles().add(customerRole.get());
			customerService.update(customer, customer.getId());
		}
	}
}
