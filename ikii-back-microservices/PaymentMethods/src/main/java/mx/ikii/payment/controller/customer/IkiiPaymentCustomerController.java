package mx.ikii.payment.controller.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.payment.payload.request.CustomerConektaRequest;

@RestController
@RequestMapping("/customerConekta")
public interface IkiiPaymentCustomerController {
	
	@PostMapping
	ResponseEntity<Object> createCustomerConekta(CustomerConektaRequest customerConektaRequest);
	
	@PutMapping
	ResponseEntity<Object> updateCustomerConekta(CustomerConektaRequest customerConektaRequest);
	
	@GetMapping("/{customerId}")
	ResponseEntity<Object> getCustomerConekta(String customerId);
	

}
