package mx.ikii.users.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface IUserClipServiceWrapper {

	UserClipResponse findById(String id);

	UserClipResponse findByUseName(String userName);

	Page<UserClipResponse> findAll(Pageable pageable);

	UserClipResponse create(UserClipRequest transaction);

	UserClipResponse update(UserClipRequest transaction, String id);

	void delete(String id);
	// ----------------------RBAC---------------------------//

	Role create(Role role);

	Privilege create(Privilege privilege);

	void assignPrivilege(String roleId, String privilegeId);

	void assignRole(String userId, String roleId);

}
