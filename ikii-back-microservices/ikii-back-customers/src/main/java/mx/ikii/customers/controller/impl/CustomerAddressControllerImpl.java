package mx.ikii.customers.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.customer.CustomerAdressRequest;
import mx.ikii.commons.payload.response.customer.CustomerAdressResponse;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;
import mx.ikii.customers.controller.ICustomerAddressController;
import mx.ikii.customers.service.ICustomerAdressServiceWrapper;

@Component
public class CustomerAddressControllerImpl implements ICustomerAddressController {

	@Autowired
	private ICustomerAdressServiceWrapper customerAddressServiceWrapper;

	@Override
	public ResponseEntity<Page<CustomerAdressResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(customerAddressServiceWrapper.getAll(pageable));
	}

	@Override
	public ResponseEntity<CustomerAdressResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(customerAddressServiceWrapper.getById(id));
	}

	@Override
	public ResponseEntity<Page<CustomerAdressResponse>> getByCustomerId(@PathVariable String customerId,
			Pageable pageable) {
		return ResponseEntity.ok(customerAddressServiceWrapper.getByCustomerId(customerId, pageable));
	}

	@Override
	public ResponseEntity<CustomerAdressResponse> create(@RequestBody CustomerAdressRequest customerAdressRequest) {
		return ResponseEntity.ok(customerAddressServiceWrapper.create(customerAdressRequest));
	}

	@Override
	public ResponseEntity<CustomerAdressResponse> update(@RequestBody CustomerAdressRequest customerAdressRequest,
			@PathVariable String id) {
		return ResponseEntity.ok(customerAddressServiceWrapper.update(customerAdressRequest, id));
	}

	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		customerAddressServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	public ResponseEntity<List<BusinessNearByMe>> nearByMe(@RequestParam Double latitude,
			@RequestParam Double longitude, @RequestParam Double distance,
			@RequestParam(required = false) String customerId,
			@RequestBody BusinessFilterRequest businessFilterRequest) {
		return ResponseEntity.ok(customerAddressServiceWrapper.nearByMe(latitude, longitude, distance, customerId,
				businessFilterRequest));
	}

}
