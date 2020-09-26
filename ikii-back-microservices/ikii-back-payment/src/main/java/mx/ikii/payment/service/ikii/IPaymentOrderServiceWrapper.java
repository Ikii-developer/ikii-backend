package mx.ikii.payment.service.ikii;

import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;

public interface IPaymentOrderServiceWrapper {

	PaymentOrderResponse order(OrderRequest paymentOrder);

	PaymentOrderResponse getById(String id);

	PaymentOrderResponse refund(OrderRequest orderRequest);
	
	PaymentOrderResponse update(OrderRequest paymentOrder);
	
}
