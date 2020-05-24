package mx.ikii.commons.feignclient.service.impl;

import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.persistence.collection.UserClip;

/**
 * This interface is used as a low layered component in feingclient process,
 * obtaining the plain UserClip resources without the http content in the
 * ResponseEntity
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public interface IUserClipFeignService {
	/**
	 * THis method is used to get the user by userId from the user microservice
	 * 
	 * @param id userId
	 * @return the user fetched
	 */

	UserClip getById(String id);

	/**
	 * 
	 * @param userName
	 * @return
	 */
	UserClip getByUserName(String userName);

	/**
	 * This method updates a user from the user microservice
	 * 
	 * @param userRequest DTO request to be updated
	 * @param id          userId
	 * @return the user updated
	 */
	UserClip update(UserClipRequest userRequest, String id);

}
