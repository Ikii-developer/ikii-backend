package mx.ikii.payment.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;
import mx.ikii.payment.service.order.IKiiPaymentOrderServiceWrapper;

@Component
public class IkiiPaymentOrderControllerImpl implements IkiiPaymentOrderController {
	
	@Autowired
	private IKiiPaymentOrderServiceWrapper ikiiPaymentServiceWrapper;

	@Override
	public ResponseEntity<OrderConektaResponse> createOrderConekta(@RequestBody OrderConektaRequest orderConektaRequest) {
		
		return ResponseEntity.ok(ikiiPaymentServiceWrapper.createOrderWithCardCharge(orderConektaRequest));
	}

	@Override
	public ResponseEntity<OrderConektaResponse> updateOrderConekta(@RequestBody OrderConektaRequest orderConektaRequest) {
		
		return ResponseEntity.ok(ikiiPaymentServiceWrapper.updateOrderConekta(orderConektaRequest));
	}

	@Override
	public ResponseEntity<OrderConektaResponse> getOrderConekta(@PathVariable String orderId) {
		
		return ResponseEntity.ok(ikiiPaymentServiceWrapper.findOrderConekta(orderId));
	}
	
	@Override
	public ResponseEntity<OrderConektaResponse> orderAuthorizeCapture(@PathVariable String orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public ResponseEntity<OrderConektaResponse> refoundOrderConekta(@RequestBody RefoundOrderRequest refoundOrderRequest) {
		
		ikiiPaymentServiceWrapper.refoundOrderConekta(refoundOrderRequest);
		
		return ResponseEntity.ok().build();
	}

}
