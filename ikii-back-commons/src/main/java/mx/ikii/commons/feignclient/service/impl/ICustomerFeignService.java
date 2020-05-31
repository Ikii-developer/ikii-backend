package mx.ikii.commons.feignclient.service.impl;

import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.persistence.collection.Customer;

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
	 * THis method is used to get the user by userId from the user microservice
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
	 * This method updates a user from the user microservice
	 * 
	 * @param userRequest DTO request to be updated
	 * @param id          userId
	 * @return the user updated
	 */
	Customer update(CustomerRequest userRequest, String id);

}
