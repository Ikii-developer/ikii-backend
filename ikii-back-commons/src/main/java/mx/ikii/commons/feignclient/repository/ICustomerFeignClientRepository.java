package mx.ikii.commons.feignclient.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.customer.CustomerRequest;
import mx.ikii.commons.payload.response.customer.CustomerAuthResponse;
import mx.ikii.commons.payload.response.customer.CustomerResponse;

/**
 * This class is used as the feign client class to interact internally with the
 * user microservice
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
@FeignClient(name = "customers")
public interface ICustomerFeignClientRepository {

	@GetMapping("customers/{id}")
	ResponseEntity<CustomerResponse> getById(@PathVariable("id") String id);

	@GetMapping("/customers/phone-numbers/{phoneNumber}")
	ResponseEntity<CustomerResponse> getByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber);

	@GetMapping("/customers/emails/{email}")
	ResponseEntity<CustomerResponse> getByEmail(@PathVariable("email") String email);

	@GetMapping("customers/emails/auth/{email}")
	ResponseEntity<CustomerAuthResponse> getByEmailForAuth(@PathVariable("email") String email);

	@GetMapping("customers/")
	ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable);

	@PutMapping("customers/{id}")
	ResponseEntity<CustomerResponse> update(@RequestBody CustomerRequest userRequest, @PathVariable("id") String id);

	@PostMapping("customers/")
	ResponseEntity<CustomerResponse> saveCustomer(@RequestBody CustomerRequest customerRequest);
}
