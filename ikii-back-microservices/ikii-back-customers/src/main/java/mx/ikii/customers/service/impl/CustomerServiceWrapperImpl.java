package mx.ikii.customers.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.mapper.customer.ICustomerMapper;
import mx.ikii.commons.payload.request.customer.CustomerRequest;
import mx.ikii.commons.payload.response.customer.CustomerAuthResponse;
import mx.ikii.commons.payload.response.customer.CustomerResponse;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.repository.ICustomerPrivilegeRepository;
import mx.ikii.customers.repository.ICustomerRoleRepository;
import mx.ikii.customers.service.ICustomerDetailsService;
import mx.ikii.customers.service.ICustomerService;
import mx.ikii.customers.service.ICustomerServiceWrapper;

/**
 * 
 * This class acts as a wrapper layer to the business logic in the UserClip service
 * 
 */
@Slf4j
@Service
public class CustomerServiceWrapperImpl implements ICustomerServiceWrapper {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private ICustomerMapper customerMapper;

  @Autowired
  private ICustomerService customerService;

  @Autowired
  private ICustomerDetailsService customerDetailsService;

  @Autowired
  private ICustomerPrivilegeRepository customerPrivilegeRepository;

  @Autowired
  private ICustomerRoleRepository customerRoleRepository;

  @Autowired
  private ControlledCacheService controlledCacheService;

  @Override
  public CustomerResponse findById(String id) {
    Page<Customer> customerClip = customerService.findAll(PageRequest.of(0, 10));
    List<CustomerResponse> usersResponse =
        customerMapper.entityToResponse(customerClip.getContent());

    usersResponse.forEach((user) -> {
      System.out.println(this.loadFromCache(user));
    });
    return customerMapper.entityToResponse(customerService.findById(id));
  }

  @Override
  public CustomerResponse testRedis(String id) {
    return null;
  }

  private void loadInCache(CustomerResponse param) {
    controlledCacheService.populateCache(param);
  }

  private CustomerResponse loadFromCache(CustomerResponse param) {
    System.out.println("Returning from Cache: -" + param);
    return controlledCacheService.getFromCache(param);
  }

  @Override
  public CustomerResponse findByemail(String email) {
    return customerMapper.entityToResponse(customerService.findByEmail(email));
  }

  @Override
  public CustomerAuthResponse findByemailForAuth(String email) {
    return customerMapper.entityToAuthResponse(customerService.findByEmail(email));
  }


  @Override
  public Page<CustomerResponse> findAll(Pageable pageable) {
    Page<Customer> customerClip = customerService.findAll(pageable);
    List<CustomerResponse> usersResponse =
        customerMapper.entityToResponse(customerClip.getContent());

    usersResponse.forEach((user) -> {
      this.loadInCache(user);
    });
    return PageHelper.createPage(usersResponse, pageable, customerClip.getTotalElements());
  }

  @Override
  public CustomerResponse signUp(CustomerRequest customerRequest) {
    Customer customer = customerMapper.requestToEntity(customerRequest);
    customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
    customer = customerService.signUp(customer);
    CustomerDetails customerDetails = CustomerDetails.builder()
        .businessFavorites(Collections.emptyList())
        .productFavorites(Collections.emptyList())
        .customerId(new ObjectId(customer.getId()))
        .isValidAccount(true).build();
    customerDetailsService.save(customerDetails);
    return customerMapper.entityToResponse(customer);
  }

  @Override
  public CustomerResponse update(CustomerRequest customerRequest, String id) {
    Customer customerEntity = customerMapper.requestToEntity(customerRequest);
    if (Nullable.isNotNull(customerRequest.getPassword())) {
      customerEntity.setPassword(bCryptPasswordEncoder.encode(customerRequest.getPassword()));
    }
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
