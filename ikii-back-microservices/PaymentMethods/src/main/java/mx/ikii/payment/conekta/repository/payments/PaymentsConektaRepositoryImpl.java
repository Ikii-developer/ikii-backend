package mx.ikii.payment.conekta.repository.payments;

import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import io.conekta.Customer;
import io.conekta.Error;
import io.conekta.ErrorList;
import io.conekta.PaymentSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PaymentsConektaRepositoryImpl implements IPaymentsConektaRepository {
	
	@Value("${conekta.api-key}")
	private String apiKey;

	@Value("${conekta.api-version}")
	private String apiVersion;

	@Override
	public Optional<PaymentSource> create(Customer customer, JSONObject paymentSourceJSON) {
		Optional<PaymentSource> paymentSource = Optional.empty();
		try {
			paymentSource = Optional.ofNullable(customer.createPaymentSource(paymentSourceJSON));
		} catch (JSONException | NoSuchFieldException | 
				IllegalArgumentException | IllegalAccessException | 
				Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
		return paymentSource;
	}

	@Override
	public void update(JSONObject paymentSourceJSON, PaymentSource paymentSourceUpdated) {
		try {
			paymentSourceUpdated.update(paymentSourceJSON);
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
	}

	@Override
	public void delete(PaymentSource paymentSourceDeleted) {
		try {
			paymentSourceDeleted.delete();
		} catch (Error | ErrorList e) {
			log.info(e.getMessage());
			e.printStackTrace();
			// TODO: throw CustomError
		}
	}
	
	

}
