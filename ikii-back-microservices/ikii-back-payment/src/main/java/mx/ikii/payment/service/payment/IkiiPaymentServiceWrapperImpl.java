package mx.ikii.payment.service.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.PaymentSource;
import mx.ikii.payment.conekta.service.payments.IPaymentsConektaService;
import mx.ikii.payment.mapper.PaymentMethodMapper;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;
import mx.ikii.payment.payload.request.PaymentSourceRequest;
import mx.ikii.payment.payload.response.PaymentMethodResponse;

@Service
public class IkiiPaymentServiceWrapperImpl implements IKiiPaymentServiceWrapper {
	
	@Autowired
	private IPaymentsConektaService paymentsConektaService;

//	@Autowired
//	private ICustomerOpenPayService customerOpenPayService;
	
	@Override
	public List<PaymentMethodResponse> getPaymentMethod(String customerId) {
		
		List<PaymentSource> paymentsSource = paymentsConektaService.find(customerId);
		return PaymentMethodMapper.paymentSourceToPaymentMethodsResponse(paymentsSource);
	}

	@Override
	public PaymentMethodResponse createPaymentMethod(String customerId, PaymentMethodDTO paymentMethodDTO) {
		
		PaymentSource paymentSource = paymentsConektaService.create(customerId, paymentMethodDTO);
		return PaymentMethodMapper.paymentSourceToPaymentMethodResponse(paymentSource);
	}

	@Override
	public void updatePaymentMethod(String customerId, PaymentSourceRequest paymentSourceRequest) {
		paymentsConektaService.update(customerId, paymentSourceRequest);
	}

	@Override
	public void deletePaymentMethod(String customerId, String paymentMethodId) {
		paymentsConektaService.delete(customerId, paymentMethodId);
	}
	
}
