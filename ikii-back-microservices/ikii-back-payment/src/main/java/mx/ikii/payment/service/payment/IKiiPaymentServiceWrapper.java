package mx.ikii.payment.service.payment;

import java.util.List;

import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;

public interface IKiiPaymentServiceWrapper {

	List<PaymentMethodResponse> getPaymentMethod(String customerId);
	
	PaymentMethodResponse createPaymentMethod(String customerId, PaymentMethodDTO paymentMethodDTO);
	
	void updatePaymentMethod(String customerId, PaymentSourceRequest paymentSourceRequest);
	
	void deletePaymentMethod(String customerId, String paymentMethodId);	
}
