package mx.ikii.users.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;

/**
 * 
 * This controller interface contains the CRUD methods related to the users
 * 
 * @author Arturo Isaac Velazquez Vargas
 * 
 */
@RestController
@RequestMapping("/")
public interface IUserClipController {

	/**
	 * This method is used to get a user by Id
	 * 
	 * @param id of the user
	 * @return the HTTP DTO response wrapping the user
	 */
	@GetMapping("{id}")
	ResponseEntity<UserClipResponse> getById(String id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/usernames/{userName}")
	ResponseEntity<UserClipResponse> getByUserName(String userName);

	/**
	 * This method is used to get all the users
	 * 
	 * @param pageable pagination
	 * @return the HTTP DTO response wrapping the users
	 */
	@GetMapping
	@PreAuthorize("hasAuthority('PRIVILEGE_ADMIN_READ')")
	ResponseEntity<Page<UserClipResponse>> getAll(Pageable pageable);

	/**
	 * This method is used to create a user resource
	 * 
	 * @param userRequest with the needed information of the resource
	 * @return the HTTP DTO response wrapping the user
	 */
	@PostMapping
	ResponseEntity<UserClipResponse> create(UserClipRequest userRequest);

	/**
	 * This method is used to update a user resource
	 * 
	 * @param userRequest user incoming DTO to be updated
	 * @param id          of the user to be updated
	 * @return the HTTP DTO response wrapping the user
	 */
	@PutMapping("{id}")
	ResponseEntity<UserClipResponse> update(UserClipRequest userRequest, String id);

	/**
	 * This method is used to delete a user resource
	 * 
	 * @param id of the user to be deleted
	 */
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);

	/**
	 * This method is used to sign up
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/sign-up")
	ResponseEntity<UserClipResponse> signUp(UserClipRequest userRequest);

	// --------------------------------RBAC--------------------------------------//
	/**
	 * 
	 * @param privilegeRequest
	 * @return
	 */
	@PostMapping("/privileges")
	ResponseEntity<Privilege> createPrivilege(Privilege privilegeRequest);

	/**
	 * 
	 * @param roleRequest
	 * @return
	 */
	@PostMapping("/roles")
	ResponseEntity<Role> createRole(Role roleRequest);

	/**
	 * 
	 * @param roleId
	 * @param privilegeId
	 * @return
	 */
	@PutMapping("/roles/{roleId}/privileges/{privilegeId}")
	ResponseEntity<Void> assignPrivilege(String roleId, String privilegeId);

	/**
	 * 
	 * @param userId
	 * @param roleId
	 * @return
	 */
	@PutMapping("/{userId}/roles/{roleId}")
	ResponseEntity<Void> assignRole(String userId, String roleId);

}
