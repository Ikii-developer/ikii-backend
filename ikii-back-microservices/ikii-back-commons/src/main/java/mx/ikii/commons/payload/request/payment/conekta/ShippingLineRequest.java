package mx.ikii.commons.payload.request.payment.conekta;

import java.util.Map;

import lombok.Data;

@Data
public class ShippingLineRequest {

	private String amount;
	private String carrier;
	private Map<String, String> metadata;
	private String tracking_number;
	
}
