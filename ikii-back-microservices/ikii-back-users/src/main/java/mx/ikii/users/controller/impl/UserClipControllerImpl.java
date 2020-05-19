package mx.ikii.users.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.users.controller.IUserClipController;
import mx.ikii.users.service.IUserClipServiceWrapper;

/**
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Component
public class UserClipControllerImpl implements IUserClipController {

	@Autowired
	private IUserClipServiceWrapper userClipServiceWrapper;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserClipResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(userClipServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<UserClipResponse> getByUserName(@PathVariable String userName) {
		return ResponseEntity.ok(userClipServiceWrapper.findByUseName(userName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Page<UserClipResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(userClipServiceWrapper.findAll(pageable));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserClipResponse> create(@RequestBody UserClipRequest userRequest) {
		return ResponseEntity.ok(userClipServiceWrapper.create(userRequest));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<UserClipResponse> update(@RequestBody UserClipRequest userRequest, @PathVariable String id) {
		return ResponseEntity.ok(userClipServiceWrapper.update(userRequest, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userClipServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<UserClipResponse> signUp(@RequestBody UserClipRequest userRequest) {
		return ResponseEntity.ok(userClipServiceWrapper.create(userRequest));
	}

	@Override
	public ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilegeRequest) {
		return ResponseEntity.ok(userClipServiceWrapper.create(privilegeRequest));
	}

	@Override
	public ResponseEntity<Role> createRole(@RequestBody Role roleRequest) {
		return ResponseEntity.ok(userClipServiceWrapper.create(roleRequest));
	}

	@Override
	public ResponseEntity<Void> assignPrivilege(@PathVariable String roleId, @PathVariable String privilegeId) {
		userClipServiceWrapper.assignPrivilege(roleId, privilegeId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Void> assignRole(@PathVariable String userId, @PathVariable String roleId) {
		userClipServiceWrapper.assignRole(userId, roleId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
