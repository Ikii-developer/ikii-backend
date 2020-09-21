package mx.ikii.payment.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.order.OrderRequest;
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
	public ResponseEntity<Void> delete(@PathVariable String orderId) {
		paymentOrderService.delete(orderId);
		return ResponseEntity.noContent().build();
	}

}
