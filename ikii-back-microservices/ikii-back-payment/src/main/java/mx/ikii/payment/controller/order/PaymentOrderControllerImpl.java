package mx.ikii.payment.controller.order;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderDeliveryTimeRequest;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.request.order.OrderStatusRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.payment.service.ikii.IPaymentOrderServiceWrapper;

@Component
public class PaymentOrderControllerImpl implements IPaymentOrderController {

  @Autowired
  private IPaymentOrderServiceWrapper paymentOrderService;

  @Override
  public ResponseEntity<PaymentOrderResponse> order(@RequestBody OrderRequest orderRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(paymentOrderService.order(orderRequest));
  }

  @Override
  public ResponseEntity<PaymentOrderResponse> update(@RequestBody OrderRequest orderRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentOrderService.update(orderRequest));
  }

  @Override
  public ResponseEntity<PaymentOrderResponse> getById(@PathVariable String orderId) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentOrderService.getById(orderId));
  }

  @Override
  public ResponseEntity<PaymentOrderResponse> refund(@RequestBody OrderRequest orderRequest) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentOrderService.refund(orderRequest));
  }

  @Override
  public ResponseEntity<Void> updateStatusOrder(@PathVariable String orderId,
      @RequestBody OrderStatusRequest orderStatusRequest) {
    paymentOrderService.updateOrderIkiiStatus(orderId, orderStatusRequest);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Override
  public ResponseEntity<List<PaymentOrderDTO>> filter(@RequestBody OrderFilter orderFilter) {
    return ResponseEntity.status(HttpStatus.OK).body(paymentOrderService.filter(orderFilter));
  }

  @Override
  public ResponseEntity<List<PaymentOrderResponse>> getByCustomerId(
      @PathVariable String customerId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(paymentOrderService.getByCustomerId(customerId));
  }

  @Override
  public ResponseEntity<Void> updateDeliveryTime(@PathVariable String orderId,
      @RequestBody OrderDeliveryTimeRequest orderStatusRequest) {
    paymentOrderService.updateOrderDeliveryTime(orderId, orderStatusRequest);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
