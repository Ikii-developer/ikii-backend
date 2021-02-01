package mx.ikii.commons.payload.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TaxLineDTO {

	private BigDecimal amount;
	private String description;
}
