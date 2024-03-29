package mx.ikii.commons.payload.request.payment.conekta;

import lombok.Data;

@Data
public class PaymentSourceRequest {
	
	private String id;
	
	private String name;
	
	private Integer exp_month;
	
	private Integer exp_year;
	
	private String address;

}
