package mx.ikii.customers.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.mapper.customer.ICustomerAdressMapper;
import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.commons.persistence.collection.CustomerAdress;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.customers.service.ICustomerAdressService;
import mx.ikii.customers.service.ICustomerAdressServiceWrapper;
import mx.ikii.customers.service.ICustomerDetailsService;

@Service
@Slf4j
public class CustomerAdressServiceWrapper implements ICustomerAdressServiceWrapper {

	@Autowired
	private ICustomerAdressService customerAdressService;

	@Autowired
	private ICustomerDetailsService customerDetailsService;

	@Autowired
	private ICustomerAdressMapper customerAdressMapper;

	@Override
	public Page<CustomerAdressResponse> getAll(Pageable pageable) {
		Page<CustomerAdress> customerAddress = customerAdressService.getAll(pageable);
		List<CustomerAdressResponse> usersResponse = customerAdressMapper
				.entityToResponse(customerAddress.getContent());
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
	public CustomerAdressResponse create(CustomerAdressRequest customerAdressRequest) {
		CustomerAdress customerAdress = customerAdressMapper.requestToEntity(customerAdressRequest);
		return customerAdressMapper.entityToResponse(customerAdressService.createCustomerAddress(customerAdress));
	}

	@Override
	public CustomerAdressResponse update(CustomerAdressRequest customerAdressRequest, String id) {
		CustomerAdress customerAdress = customerAdressMapper.requestToEntity(customerAdressRequest);
		return customerAdressMapper.entityToResponse(customerAdressService.updateCustomerAddress(customerAdress, id));
	}

	@Override
	public void delete(String customerAddressId) {
		customerAdressService.deleteCustomerAddress(customerAddressId);
	}

	@Override
	public List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, Double maxDistance, String customerId,
			BusinessFilterRequest businessFilterRequest) {
		maxDistance = (Nullable.isNull(maxDistance) ? 1.0 : maxDistance); // 1.0 == 1 KM

		List<BusinessNearByMe> businessAddress = customerAdressService.nearByMe(latitude, longitude, maxDistance,
				businessFilterRequest.getKeywords());
		if (Nullable.isNullOrEmpty(businessAddress)) {
			maxDistance = 7.0;
			businessAddress = customerAdressService.nearByMe(latitude, longitude, maxDistance,
					businessFilterRequest.getKeywords());
		}
		if (Nullable.isNullOrEmpty(businessAddress))
			throw new ResourceNotFoundException("Business Not Found");

		DecimalFormat df = new DecimalFormat("#");
        businessAddress.forEach(e -> {
          
          BigDecimal bd = new BigDecimal(Double.parseDouble(df.format(e.getDistance() * 1000)));
          bd = bd.setScale(2, RoundingMode.HALF_UP);
          e.setDistance(bd.doubleValue());
        });

		setFavorites(businessAddress, customerId);

		return businessAddress;
	}

	private void setFavorites(List<BusinessNearByMe> businessAddress, String customerId) {
		if (Nullable.isNotNull(customerId)) {
			try {
				CustomerDetails customerDetails = customerDetailsService.getByCustomerId(customerId);
				List<ObjectId> businessFavorites = customerDetails.getBusinessFavorites();
				if (!Nullable.isNullOrEmpty(businessFavorites)) {
					businessAddress = businessAddress.stream().map(businessMap -> {
						if (businessFavorites.contains(new ObjectId(businessMap.getId()))) {
							businessMap.setFavorite(true);
						}
						return businessMap;
					}).collect(Collectors.toList());
				}
			} catch (Exception e) {
				log.warn("Not possible to get CustomerDetails for customerId {}, {}", customerId, e.getMessage());
			}
		}
	}

}
