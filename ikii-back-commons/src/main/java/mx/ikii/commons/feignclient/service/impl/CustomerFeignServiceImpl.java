package mx.ikii.commons.feignclient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.feignclient.repository.ICustomerFeignClientRepository;
import mx.ikii.commons.mapper.customer.ICustomerDetailsMapper;
import mx.ikii.commons.mapper.customer.ICustomerMapper;
import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.customer.CustomerRequest;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;
import mx.ikii.commons.utils.ResponseEntityHelper;

/**
 * @author Arturo Isaac Velazquez Vargas
 */
@Service
public class CustomerFeignServiceImpl implements ICustomerFeignService {

  @Autowired
  private ICustomerFeignClientRepository customerFeignClientRepository;

  @Autowired
  private ICustomerMapper customerMapper;

  @Autowired
  private ICustomerDetailsMapper customerDetailsMapper;

  @Override
  public Customer getById(String id) {
    return customerMapper.responseToEntity(
        ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getById(id)));
  }

  @Override
  public Customer update(CustomerRequest userRequest, String id) {
    return customerMapper.responseToEntity(ResponseEntityHelper
        .processingHttpStatus(customerFeignClientRepository.update(userRequest, id)));
  }

  @Override
  public Customer getByPhoneNumber(String userName) {
    return customerMapper.responseToEntity(ResponseEntityHelper
        .processingHttpStatus(customerFeignClientRepository.getByPhoneNumber(userName)));
  }

  @Override
  public Customer getByEmail(String email) {
    return customerMapper.responseToEntity(
        ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository.getByEmail(email)));
  }

  @Override
  public Customer getByEmailForAuth(String email) {
    return customerMapper.authResponseToentity(ResponseEntityHelper
        .processingHttpStatus(customerFeignClientRepository.getByEmailForAuth(email)));
  }

  @Override
  public CustomerDetails getCustomerDetailsByCustomerId(String customerId) {
    return customerDetailsMapper.responseToEntity(ResponseEntityHelper.processingHttpStatus(
        customerFeignClientRepository.getCustomerDetailsByCustomerId(customerId)));
  }

  @Override
  public List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, Double distance,
      String customerId, BusinessFilterRequest businessFilterRequest) {
    return ResponseEntityHelper.processingHttpStatus(customerFeignClientRepository
        .nearByMe(latitude, longitude, distance, customerId, businessFilterRequest));
  }
}
