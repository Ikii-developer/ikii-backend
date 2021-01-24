package mx.ikii.commons.feignclient.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaRequest;

@FeignClient(name = "payment-method")
public interface IPaymentCustomerFeignClientRepository {

	@PostMapping("/customer-payment")
	ResponseEntity<Object> createCustomerConekta(@RequestBody CustomerConektaRequest customerConektaRequest);
	
	@PutMapping("/customer-payment")
	ResponseEntity<Object> updateCustomerConekta(@RequestBody CustomerConektaRequest customerConektaRequest);
	
	@GetMapping("/customer-payment/{customerId}")
	ResponseEntity<Object> getCustomerConekta(@PathVariable String customerId);

}
