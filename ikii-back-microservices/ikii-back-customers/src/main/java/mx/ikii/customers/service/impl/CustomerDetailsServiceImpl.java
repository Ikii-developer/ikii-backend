package mx.ikii.customers.service.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.customers.helper.CustomerDetailsHelper;
import mx.ikii.customers.repository.ICustomerDetailsRepository;
import mx.ikii.customers.service.ICustomerDetailsService;

@Service
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

  @Autowired
  private ICustomerDetailsRepository customerDetailsRepository;

  @Override
  public CustomerDetails getById(String id) {
    return customerDetailsRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id, CustomerDetails.class));
  }

  @Override
  public CustomerDetails getByCustomerId(String id) {
    return customerDetailsRepository.findByCustomerId(new ObjectId(id))
        .orElseThrow(() -> new ResourceNotFoundException(
            "Resource [CustomerDetails] with customerId: 5f338ee5adbe1d0001a7402c not found"));
  }

  @Override
  public List<CustomerDetails> getAll(Pageable pageable) {
    return customerDetailsRepository.findAll(pageable).getContent();
  }

  @Override
  public CustomerDetails save(CustomerDetails customerDetails) {
    return customerDetailsRepository.save(customerDetails);
  }

  @Override
  public CustomerDetails update(CustomerDetails customerDetails, String id) {
    CustomerDetails customerDetailsEntity = customerDetailsRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id, CustomerDetails.class));
    CustomerDetailsHelper.setUpdateProperties(customerDetailsEntity, customerDetails);
    customerDetailsRepository.save(customerDetailsEntity);
    return customerDetailsEntity;
  }

  @Override
  public void delete(String id) {
    customerDetailsRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(id, CustomerDetails.class));
    customerDetailsRepository.deleteById(id);
  }

}
