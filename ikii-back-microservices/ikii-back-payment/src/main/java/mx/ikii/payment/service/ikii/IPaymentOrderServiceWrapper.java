package mx.ikii.payment.service.ikii;

import mx.ikii.commons.payload.request.order.OrderRequest;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;

public interface IPaymentOrderServiceWrapper {

	PaymentOrderResponse order(OrderRequest paymentOrder);

	PaymentOrderResponse getById(String id);

	void delete(String id);
	
	PaymentOrderResponse update(OrderRequest paymentOrder);
	
}
