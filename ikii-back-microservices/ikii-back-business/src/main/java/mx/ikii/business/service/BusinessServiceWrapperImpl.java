package mx.ikii.business.service;

import java.util.List;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.domain.BusinessStatus;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.mapper.business.IBusinessMapper;
import mx.ikii.commons.payload.request.business.BusinessRequest;
import mx.ikii.commons.payload.response.business.BusinessResponse;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;

@Service
@Slf4j
public class BusinessServiceWrapperImpl implements IBusinessServiceWrapper {

  @Autowired
  private IBusinessMapper businessMapper;

  @Autowired
  private IBusinessService businessService;

  @Autowired
  private IBusinessCategoryService businessCategoryService;

  @Autowired
  private ICustomerFeignService customerFeignService;

  @Override
  public BusinessResponse findById(String id) {
    return businessMapper.entityToResponse(businessService.findById(id));
  }

  @Override
  public BusinessResponse getByBusinesName(String businessName) {
    return businessMapper.entityToResponse(businessService.findByName(businessName));
  }

  @Override
  public List<BusinessResponse> filterByBusinessName(String businessName) {
    return businessMapper.entityToResponse(businessService.filterByBusinessName(businessName));
  }

  @Override
  public Page<BusinessResponse> findAll(Pageable pageable, String customerId) {
    log.info("Find all business with customerId {}", customerId);
    Page<Business> businessPage = businessService.findAll(pageable);
    List<BusinessResponse> businessList =
        businessMapper.entityToResponse(businessPage.getContent());
    if (Nullable.isNotNull(customerId)) {
      try {
        CustomerDetails customerDetails =
            customerFeignService.getCustomerDetailsByCustomerId(customerId);
        List<ObjectId> businessFavorites = customerDetails.getBusinessFavorites();
        if (!Nullable.isNullOrEmpty(businessFavorites)) {
          businessList = businessList.stream().map(businessMap -> {
            if (businessFavorites.contains(new ObjectId(businessMap.getId()))) {
              businessMap.setFavorite(true);
            }
            return businessMap;
          }).collect(Collectors.toList());
        }
      } catch (Exception e) {
        log.warn("Not possible to get CustomerDetails for customerId {}, {}", customerId,
            e.getMessage());
      }
    }
    return PageHelper.createPage(businessList, pageable, businessPage.getTotalElements());
  }

  @Override
  public List<BusinessResponse> findAllFeign() {
    log.info("In findAllFeign");
    return businessMapper.entityToResponse(businessService.findAll(Pageable.unpaged()).getContent());
  }

  @Override
  public BusinessResponse create(BusinessRequest businessRequest) {
    log.info("Craeting business with businessId {} and customerId {} and categoryId {}",
        businessRequest.getId(),
        businessRequest.getCustomerId(), businessRequest.getCategoryId());
    log.info("Request: {}", businessRequest);
    businessCategoryService.findById(businessRequest.getCategoryId());
    try {
      customerFeignService.getById(businessRequest.getCustomerId());
    } catch (Exception e) {
      log.error(e.getLocalizedMessage(), e);
      throw new ResourceNotFoundException(businessRequest.getCustomerId(), Customer.class);
    }
    Business businessEntity = businessMapper.requestToEntity(businessRequest);
    businessEntity.setStatus(BusinessStatus.ACTIVE.getName());
    businessEntity.setIsOpen(true);
    return businessMapper.entityToResponse(businessService.create(businessEntity));
  }

  @Override
  public List<BusinessResponse> create(List<BusinessRequest> businessRequest) {
    return businessRequest.stream().map(business -> {
      return create(business);
    }).collect(Collectors.toList());
  }

  @Override
  public BusinessResponse update(BusinessRequest businessRequest, String id) {
    Business businessEntity = businessMapper.requestToEntity(businessRequest);
    return businessMapper.entityToResponse(businessService.update(businessEntity, id));
  }

  @Override
  public void delete(String id) {
    businessService.delete(id);
  }

	@Override
	public BusinessResponse getByCustomerId(String customerId) {
		return businessMapper.entityToResponse(businessService.findByCustomerId(customerId));
	}

}
