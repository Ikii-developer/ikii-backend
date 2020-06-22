package mx.ikii.payment.conekta.service.payments;

import io.conekta.PaymentSource;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;


public interface IPaymentsConektaService {
	
	PaymentSource create(String customerId, PaymentMethodDTO paymentMethodDTO);
	
	void update(String customerId, PaymentSourceRequest paymentSourceRequest);
	
	void delete(String customerId);

}
