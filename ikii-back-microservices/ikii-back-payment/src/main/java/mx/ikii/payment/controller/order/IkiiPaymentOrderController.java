package mx.ikii.payment.controller.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;
@RestController
@RequestMapping("/order-payment")
public interface IkiiPaymentOrderController {
	
	@PostMapping
	ResponseEntity<OrderConektaResponse> createOrderConekta(OrderConektaRequest orderConektaRequest);
	
	@PutMapping
	ResponseEntity<OrderConektaResponse> updateOrderConekta(OrderConektaRequest orderConektaRequest);
	
	@GetMapping("/{orderId}")
	ResponseEntity<OrderConektaResponse> getOrderConekta(String orderId);
	
	@GetMapping("/capture/{orderId}")
	ResponseEntity<OrderConektaResponse> orderAuthorizeCapture(String orderId);
	
	@PutMapping("/refound")
	ResponseEntity<OrderConektaResponse> refoundOrderConekta(RefoundOrderRequest refoundOrderRequest);
	
}
