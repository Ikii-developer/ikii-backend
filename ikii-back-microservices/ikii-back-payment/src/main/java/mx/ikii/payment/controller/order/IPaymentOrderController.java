package mx.ikii.payment.controller.order;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderDeliveryTimeRequest;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.request.order.OrderStatusRequest;
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
    
    @GetMapping("/customers/{customerId}")
    ResponseEntity<List<PaymentOrderResponse>> getByCustomerId(String customerId);
    
    @PutMapping("/refund")
    ResponseEntity<PaymentOrderResponse> refund(OrderRequest orderRequest);
    
    @PatchMapping("/updateStatusOrder/{orderId}")
    ResponseEntity<Void> updateStatusOrder(String orderId, OrderStatusRequest orderStatusRequest);
    
    @PatchMapping("/updateDeliveryTime/{orderId}")
    ResponseEntity<Void> updateDeliveryTime(String orderId, OrderDeliveryTimeRequest orderStatusRequest);
    
    @PostMapping("/filter")
    ResponseEntity<List<PaymentOrderDTO>> filter(OrderFilter orderFilter);
    
}
