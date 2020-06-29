package mx.ikii.payment.mapper;

import java.util.ArrayList;
import java.util.List;

import io.conekta.PaymentSource;
import mx.ikii.payment.payload.response.PaymentMethodResponse;

public class PaymentMethodMapper {
	
	public static PaymentMethodResponse paymentSourceToPaymentMethodResponse(PaymentSource paymentSource) {
		PaymentMethodResponse pay = new PaymentMethodResponse();
		
		pay.setId(paymentSource.id);
		pay.setType(paymentSource.type);
		pay.setLast4(paymentSource.getVal("last4").toString());
		pay.setExp_month(paymentSource.getVal("exp_month").toString());
		pay.setExp_year(paymentSource.getVal("exp_year").toString());
		pay.setBrand(paymentSource.getVal("brand").toString());
		pay.setName(paymentSource.getVal("name").toString());
		
		return pay;
	}
	
	public static List<PaymentMethodResponse> paymentSourceToPaymentMethodsResponse(List<PaymentSource> paymentsSource) {
		List<PaymentMethodResponse> response = new ArrayList<>();
		
		paymentsSource.forEach(p->{
			PaymentMethodResponse pay = new PaymentMethodResponse();
			pay.setId(p.id);
			pay.setType(p.type);
			pay.setLast4(p.getVal("last4").toString());
			pay.setExp_month(p.getVal("exp_month").toString());
			pay.setExp_year(p.getVal("exp_year").toString());
			pay.setBrand(p.getVal("brand").toString());
			pay.setName(p.getVal("name").toString());
			response.add(pay);
		});
		
		return response;
	}

}
