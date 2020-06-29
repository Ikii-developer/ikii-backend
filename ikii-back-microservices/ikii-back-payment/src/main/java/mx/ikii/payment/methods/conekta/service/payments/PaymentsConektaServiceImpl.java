package mx.ikii.payment.methods.conekta.service.payments;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.ConektaList;
import io.conekta.Customer;
import io.conekta.PaymentSource;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.payment.methods.conekta.repository.payments.IPaymentsConektaRepository;
import mx.ikii.payment.methods.conekta.service.customer.ICustomerConektaService;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;

/**
 * ************************************************************************************************************************
 * ************			PAYMENT METHODS				***********************************************************************
 * ************************************************************************************************************************
 * El objeto Payment Source describe el método de pago. 
 * 		Este pago puede ser online (pagos con tarjeta) o offline (OxxoPay). 
 * 		Recuerda que tendras que agregar un webhook listener para los pagos offline.
 * 
 * type: Tipo del método de pago. Por el momento los único tipos permitido son "card" y "oxxo_recurrent".
 * token_id: Id de token que será utilizado para crear un método de pago de tipo "card". 
 * 		Ve el tutorial de suscripciones para más información sobre cómo tokenizar tarjetas.
 * 
 * 	https://developers.conekta.com/api?language=java#payment-source
 */
@Slf4j
@Service
public class PaymentsConektaServiceImpl implements IPaymentsConektaService {
	
	@Autowired
	private ICustomerConektaService customerConektaService;
	
	@Autowired
	private IPaymentsConektaRepository paymentsConektaRepository;
	

	@Override
	public List<PaymentSource> find(String customerId) {
		Customer customer = customerConektaService.findCurstomer(customerId);
		ConektaList paymentSources = customer.payment_sources;
		return paymentSources;
	}
	
	@Override
	public PaymentSource create(String customerId, PaymentMethodDTO paymentMethodDTO) {
//		JSONObject paymentSourceJSON = new JSONObject("{"
//				  + "'token_id': 'tok_test_visa_4242',"
//				  + "'type': 'card'"
//				+ "}");
		Customer customer = customerConektaService.findCurstomer(customerId);
		JSONObject paymentSourceJSON = new JSONObject(paymentMethodDTO);
		PaymentSource paymentSource = paymentsConektaRepository.create(customer, paymentSourceJSON);
		// TODO: throw CustomError
		return paymentSource;
	}

	/**
	 * Actualizar Método de Pago
	 * 
	 * id: Id del método de pago.
	 * name: Nombre del tarjeta habiente.
	 * exp_month: Mes de expiración de la tarjeta.
	 * exp_year: Año de expiración de la tarjeta.
	 * address: Dirección del tarjethabiente.
	 */
	@Override
	public void update(String customerId, PaymentSourceRequest paymentSourceRequest) {
//		JSONObject paymentSourceUpdateJSON = new JSONObject("{'exp_month': '11'}");
		
		Customer customer = customerConektaService.findCurstomer(customerId);
		
		JSONObject paymentSourceJSON = new JSONObject(paymentSourceRequest);
		
		PaymentSource paymentSourceUpdated = (PaymentSource) customer.payment_sources.get(0);
		
		paymentsConektaRepository.update(paymentSourceJSON, paymentSourceUpdated);
		
	}

	@Override
	public void delete(String customerId, String paymentMethodId) {
		
		List<PaymentSource> paymentsSource = find(customerId);
		paymentsSource.forEach(p->{
			if(p.id.equals(paymentMethodId)) {
				paymentsConektaRepository.delete(p);
			}
		});
		
	}

}
