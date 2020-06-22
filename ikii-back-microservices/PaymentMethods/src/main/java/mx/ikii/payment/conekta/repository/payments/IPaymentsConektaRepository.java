package mx.ikii.payment.conekta.repository.payments;

import java.util.Optional;

import org.json.JSONObject;

import io.conekta.Customer;
import io.conekta.PaymentSource;

public interface IPaymentsConektaRepository {

	Optional<PaymentSource> create(Customer customer, JSONObject paymentSourceJSON);

	void update(JSONObject paymentSourceJSON, PaymentSource paymentSourceUpdated);

	void delete(PaymentSource paymentSourceDeleted);

}
