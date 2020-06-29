package mx.ikii.payment.controller.payment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;

@RestController
@RequestMapping("/payment-method")
public interface IkiiPaymentController {

	@GetMapping("/{customerId}")
	ResponseEntity<List<PaymentMethodResponse>> getPaymentMethod(String customerId);
	
	@PostMapping("/{customerId}")
	ResponseEntity<PaymentMethodResponse> createPaymentMethod(String customerId, PaymentMethodDTO paymentMethodDTO);
	
	@PutMapping("/{customerId}")
	ResponseEntity<PaymentMethodResponse> updatePaymentMethod(String customerId, PaymentSourceRequest paymentSourceRequest);
	
	@DeleteMapping("/{customerId}/{paymentMethodId}")
	ResponseEntity<Void> deletePaymentMethod(String customerId, String paymentMethodId);
	
}
