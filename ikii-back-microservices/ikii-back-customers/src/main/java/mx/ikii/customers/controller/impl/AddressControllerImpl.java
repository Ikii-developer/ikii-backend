package mx.ikii.customers.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.customers.controller.IAddressController;
import mx.ikii.customers.service.ICustomerAdressServiceWrapper;

@Component
public class AddressControllerImpl implements IAddressController {

	@Autowired
	private ICustomerAdressServiceWrapper customerAddressServiceWrapper;

	@Override
	public ResponseEntity<CustomerAdressResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(customerAddressServiceWrapper.getById(id));
	}

	@Override
	public ResponseEntity<Page<CustomerAdressResponse>> getByCustomerId(@PathVariable String customerId,
			Pageable pageable) {
		return ResponseEntity.ok(customerAddressServiceWrapper.getByCustomerId(customerId,pageable));
	}

	@Override
	public ResponseEntity<CustomerAdressResponse> create(@RequestBody CustomerAdressRequest request) {
		return ResponseEntity.ok(customerAddressServiceWrapper.create(request));
	}

	@Override
	public ResponseEntity<CustomerAdressResponse> update(@RequestBody CustomerAdressRequest request,
			@PathVariable String id) {
		return ResponseEntity.ok(customerAddressServiceWrapper.update(request, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		customerAddressServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
