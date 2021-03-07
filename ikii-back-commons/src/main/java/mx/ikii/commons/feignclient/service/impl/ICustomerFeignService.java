package mx.ikii.commons.feignclient.service.impl;

import java.util.List;
import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.customer.CustomerRequest;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;

/**
 * This interface is used as a low layered component in feingclient process,
 * obtaining the plain UserClip resources without the http content in the
 * ResponseEntity
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public interface ICustomerFeignService {
	/**
	 * THis method is used to get the customer by userId from the user microservice
	 * 
	 * @param id userId
	 * @return the user fetched
	 */
	Customer getById(String id);

	/**
	 * 
	 * @param phoneNumber
	 * @return
	 */
	Customer getByPhoneNumber(String phoneNumber);

	/**
	 * This method is used to get the customer by email
	 * 
	 * @param email
	 * @return
	 */
	Customer getByEmail(String email);

	/**
	 * This method is only used for authentication purpose
	 * 
	 * @param email
	 * @return
	 */
	Customer getByEmailForAuth(String email);

	/**
	 * This method updates a user from the user microservice
	 * 
	 * @param userRequest DTO request to be updated
	 * @param id          userId
	 * @return the user updated
	 */
	Customer update(CustomerRequest userRequest, String id);

	/**
	 * 
	 * @param id userId
	 * @return the user fetched
	 */
	CustomerDetails getCustomerDetailsByCustomerId(String customerId);
	
	
	List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, 
			Double distance, String customerId, BusinessFilterRequest businessFilterRequest);

}
