package mx.ikii.payment.service.ikii;

import mx.ikii.commons.persistence.collection.PaymentOrder;

public interface IPaymentOrderService {

	PaymentOrder getById(String id);
	
	PaymentOrder save(PaymentOrder paymentOrder);
	
	PaymentOrder update(PaymentOrder paymentOrder);

	void calculateTotals(PaymentOrder paymentOrder);
	
}
