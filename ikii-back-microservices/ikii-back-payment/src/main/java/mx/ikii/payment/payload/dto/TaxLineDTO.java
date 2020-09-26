package mx.ikii.payment.payload.dto;

import lombok.Data;

@Data
public class TaxLineDTO {

	private Integer amount;
	private String description;
	
}
