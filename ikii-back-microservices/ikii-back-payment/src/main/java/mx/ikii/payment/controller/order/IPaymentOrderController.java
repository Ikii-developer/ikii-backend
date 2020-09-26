package mx.ikii.payment.controller.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;

@RestController
@RequestMapping("/orders")
public interface IPaymentOrderController {
	
	@PostMapping
	ResponseEntity<PaymentOrderResponse> order(OrderRequest orderRequest);
	
	@PutMapping
	ResponseEntity<PaymentOrderResponse> update(OrderRequest orderRequest);
	
	@GetMapping("/{orderId}")
	ResponseEntity<PaymentOrderResponse> getById(String orderId);
	
	@PutMapping("/refund")
	ResponseEntity<PaymentOrderResponse> refund(OrderRequest orderRequest);
	
	
}
