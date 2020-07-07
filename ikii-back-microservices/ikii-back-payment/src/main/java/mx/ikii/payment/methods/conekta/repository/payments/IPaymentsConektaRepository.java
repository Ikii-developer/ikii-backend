package mx.ikii.payment.methods.conekta.repository.payments;

import org.json.JSONObject;

import io.conekta.Customer;
import io.conekta.PaymentSource;

public interface IPaymentsConektaRepository {
	
	PaymentSource create(Customer customer, JSONObject paymentSourceJSON);

	void update(JSONObject paymentSourceJSON, PaymentSource paymentSourceUpdated);

	void delete(PaymentSource paymentSourceDeleted);

}
