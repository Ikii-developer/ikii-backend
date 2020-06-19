package mx.ikii.customers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import mx.ikii.commons.payload.request.customer.CustomerDetailsRequest;
import mx.ikii.commons.payload.response.customer.CustomerDetailsResponse;
import mx.ikii.customers.controller.ICustomerDetailsController;
import mx.ikii.customers.service.ICustomerDetailsServiceWrapper;

@Component
public class CustomerDetailsControllerImpl implements ICustomerDetailsController{

	@Autowired
	private ICustomerDetailsServiceWrapper customerDetailsServiceWrapper;

	@Override
	public ResponseEntity<CustomerDetailsResponse> getById(String id) {
		return ResponseEntity.ok(customerDetailsServiceWrapper.getById(id));
	}

	@Override
	public ResponseEntity<CustomerDetailsResponse> getByCustomerId(String customerId) {
		return ResponseEntity.ok(customerDetailsServiceWrapper.getByCustomerId(customerId));
	}

	@Override
	public ResponseEntity<Page<CustomerDetailsResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(customerDetailsServiceWrapper.getAll(pageable));
	}

	@Override
	public ResponseEntity<CustomerDetailsResponse> create(CustomerDetailsRequest customerDetails) {
		return ResponseEntity.ok(customerDetailsServiceWrapper.create(customerDetails));
	}

	@Override
	public ResponseEntity<CustomerDetailsResponse> update(CustomerDetailsRequest customerDetails, String id) {
		return ResponseEntity.ok(customerDetailsServiceWrapper.update(customerDetails,id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		customerDetailsServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

}
