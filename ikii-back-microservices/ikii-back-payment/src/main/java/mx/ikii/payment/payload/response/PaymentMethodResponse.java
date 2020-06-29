package mx.ikii.payment.payload.response;

import lombok.Data;

@Data
public class PaymentMethodResponse {

	private String id;
	
	private String type;
	
	private String last4;
	
	private String exp_month;
	
	private String exp_year;
	
	private String brand;
	
	private String name;
	
}
