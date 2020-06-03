package mx.ikii.users.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.user.CustomerRequest;
import mx.ikii.commons.payload.response.user.CustomerResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;

@RestController
@RequestMapping("/")
public interface ICustomerController {

	/**
	 * This method is used to get a user by Id
	 * 
	 * @param id of the user
	 * @return the HTTP DTO response wrapping the user
	 */
	@GetMapping("{id}")
	ResponseEntity<CustomerResponse> getById(String id);

	/**
	 * This method is used to get a user by email
	 * 
	 * @param email
	 * @return
	 */
	@GetMapping("/emails/{email}")
	ResponseEntity<CustomerResponse> getByEmail(String email);

	/**
	 * This method is used to get a user by telephone
	 * 
	 * @param telephone
	 * @return
	 */
	@GetMapping("/phone-numbers/{phoneNumber}")
	ResponseEntity<CustomerResponse> getByPhoneNumber(String phoneNumber);

	/**
	 * This method is used to get all the users
	 * 
	 * @param pageable pagination
	 * @return the HTTP DTO response wrapping the users
	 */
	@GetMapping
	ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable);

	/**
	 * This method is used to update a user resource
	 * 
	 * @param userRequest user incoming DTO to be updated
	 * @param id          of the user to be updated
	 * @return the HTTP DTO response wrapping the user
	 */
	@PutMapping("{id}")
	ResponseEntity<CustomerResponse> update(CustomerRequest userRequest, String id);

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
	ResponseEntity<CustomerResponse> signUp(CustomerRequest userRequest);

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
