package mx.ikii.payment.payload.dto;

import lombok.Data;

@Data
public class PaymentMethodDTO {

	private String type;
	private String token_id;
	
}
