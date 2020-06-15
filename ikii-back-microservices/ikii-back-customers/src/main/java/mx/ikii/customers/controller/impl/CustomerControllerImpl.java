package mx.ikii.customers.controller.impl;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.customer.CustomerRequest;
import mx.ikii.commons.payload.response.user.CustomerAuthResponse;
import mx.ikii.commons.payload.response.user.CustomerResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.customers.controller.ICustomerController;
import mx.ikii.customers.service.ICustomerServiceWrapper;

@Component
public class CustomerControllerImpl implements ICustomerController {

	@Autowired
	private ICustomerServiceWrapper customerServiceWrapper;

	@Override
	public ResponseEntity<CustomerResponse> signUp(@RequestBody @Valid CustomerRequest userRequest) {
		return ResponseEntity.ok(customerServiceWrapper.signUp(userRequest));
	}

	@Override
	public ResponseEntity<CustomerResponse> getById(@PathVariable @NotEmpty String id) {
		return ResponseEntity.ok(customerServiceWrapper.findById(id));
	}

	@Override
	public ResponseEntity<CustomerResponse> getByEmail(@PathVariable @NotEmpty String email) {
		return ResponseEntity.ok(customerServiceWrapper.findByemail(email));
	}

	@Override
	public ResponseEntity<CustomerAuthResponse> getByEmailForAuth(@PathVariable String email) {
		return ResponseEntity.ok(customerServiceWrapper.findByemailForAuth(email));
	}

	@Override
	public ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(customerServiceWrapper.findAll(pageable));
	}

	@Override
	public ResponseEntity<CustomerResponse> update(@RequestBody CustomerRequest userRequest,
			@PathVariable @NotEmpty String id) {
		return ResponseEntity.ok(customerServiceWrapper.update(userRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		customerServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Privilege> createPrivilege(@RequestBody Privilege privilegeRequest) {
		return ResponseEntity.ok(customerServiceWrapper.create(privilegeRequest));
	}

	@Override
	public ResponseEntity<Role> createRole(@RequestBody Role roleRequest) {
		return ResponseEntity.ok(customerServiceWrapper.create(roleRequest));
	}

	@Override
	public ResponseEntity<Void> assignPrivilege(@PathVariable String roleId, @PathVariable String privilegeId) {
		customerServiceWrapper.assignPrivilege(roleId, privilegeId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<Void> assignRole(@PathVariable String userId, @PathVariable String roleId) {
		customerServiceWrapper.assignRole(userId, roleId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<CustomerResponse> getByPhoneNumber(@PathVariable String phoneNumber) {
		return ResponseEntity.ok(customerServiceWrapper.findByPhoneNumber(phoneNumber));
	}

}
