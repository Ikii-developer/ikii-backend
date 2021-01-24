package mx.ikii.commons.feignclient.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.payment.conekta.OrderConektaRequest;
import mx.ikii.commons.payload.request.payment.conekta.RefoundOrderRequest;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;

@FeignClient(name = "payment-method")
public interface IPaymentOrderFeignClientRepository {

	@PostMapping("/order-payment")
	ResponseEntity<OrderConektaResponse> createOrderConekta(@RequestBody OrderConektaRequest orderConektaRequest);
	
	@PutMapping("/order-payment")
	ResponseEntity<OrderConektaResponse> updateOrderConekta(@RequestBody OrderConektaRequest orderConektaRequest);
	
	@GetMapping("/order-payment/{orderId}")
	ResponseEntity<OrderConektaResponse> getOrderConekta(@PathVariable String orderId);
	
	@GetMapping("/order-payment/capture/{orderId}")
	ResponseEntity<OrderConektaResponse> orderAuthorizeCapture(@PathVariable String orderId);
	
	@PutMapping("/order-payment/refound")
	ResponseEntity<OrderConektaResponse> refoundOrderConekta(@RequestBody RefoundOrderRequest refoundOrderRequest);

}
