package mx.ikii.payment.controller.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;
import mx.ikii.payment.payload.response.OrderConektaResponse;

@RestController
@RequestMapping("/orderConekta")
public interface IkiiPaymentOrderController {
	
	@PostMapping
	ResponseEntity<OrderConektaResponse> createOrderConekta(OrderConektaRequest orderConektaRequest);
	
	@PutMapping
	ResponseEntity<OrderConektaResponse> updateOrderConekta(OrderConektaRequest orderConektaRequest);
	
	@GetMapping("/{id}")
	ResponseEntity<OrderConektaResponse> getOrderConekta(String orderId);
	

}
