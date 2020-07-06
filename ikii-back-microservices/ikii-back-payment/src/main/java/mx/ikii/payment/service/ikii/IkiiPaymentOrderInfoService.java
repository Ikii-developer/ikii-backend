package mx.ikii.payment.service.ikii;

import mx.ikii.commons.persistence.collection.PaymentOrderInfo;

public interface IkiiPaymentOrderInfoService {
	
	PaymentOrderInfo create(PaymentOrderInfo paymentOrderInfo);
	
	PaymentOrderInfo findById(String paymentInfoId);
	
	PaymentOrderInfo update(PaymentOrderInfo paymentOrderInfo);
	
	void delete(PaymentOrderInfo paymentOrderInfo);

}
