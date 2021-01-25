package mx.ikii.commons.feignclient.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.dto.PaymentMethodDTO;
import mx.ikii.commons.payload.request.payment.conekta.CustomerConektaRequest;
import mx.ikii.commons.payload.request.payment.conekta.OrderConektaRequest;
import mx.ikii.commons.payload.request.payment.conekta.PaymentSourceRequest;
import mx.ikii.commons.payload.request.payment.conekta.RefoundOrderRequest;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.commons.payload.response.payment.conekta.PaymentMethodResponse;

@FeignClient(name = "payment-method")
public interface IPaymentMethodFeignClientRepository {

	@GetMapping("/payment-method/{customerId}")
	ResponseEntity<List<PaymentMethodResponse>> getPaymentMethod(@PathVariable String customerId);
	
	@PostMapping("/payment-method/{customerId}")
	ResponseEntity<PaymentMethodResponse> createPaymentMethod(@PathVariable String customerId, @RequestBody PaymentMethodDTO paymentMethodDTO);
	
	@PutMapping("/payment-method/{customerId}")
	ResponseEntity<PaymentMethodResponse> updatePaymentMethod(@PathVariable String customerId, @RequestBody PaymentSourceRequest paymentSourceRequest);
	
	@DeleteMapping("/payment-method/{customerId}/{paymentMethodId}")
	ResponseEntity<Void> deletePaymentMethod(@PathVariable String customerId, @PathVariable String paymentMethodId);
	
	//********************
	
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
	
	//**************************+
	
	@PostMapping("/customer-payment")
	ResponseEntity<Object> createCustomerConekta(@RequestBody CustomerConektaRequest customerConektaRequest);
	
	@PutMapping("/customer-payment")
	ResponseEntity<Object> updateCustomerConekta(@RequestBody CustomerConektaRequest customerConektaRequest);
	
	@GetMapping("/customer-payment/{customerId}")
	ResponseEntity<Object> getCustomerConekta(@PathVariable String customerId);

}
