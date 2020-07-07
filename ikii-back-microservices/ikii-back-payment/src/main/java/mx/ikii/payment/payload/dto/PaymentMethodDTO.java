package mx.ikii.payment.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentMethodDTO {

	private String type;
	private String token_id;
	
}
