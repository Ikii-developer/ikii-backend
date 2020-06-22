package mx.ikii.payment.payload.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class ChargesDTO {
	
	private String id;
	
	private List<PaymentMethodDTO> paymentMethod;
	
	private BigDecimal amount;
	
}
