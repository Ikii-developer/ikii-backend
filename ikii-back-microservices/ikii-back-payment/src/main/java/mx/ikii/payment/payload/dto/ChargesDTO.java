package mx.ikii.payment.payload.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Lista de los cargos que se generaron para cubrir el monto de la orden.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargesDTO {
	
	private String id;
	
	private List<PaymentMethodDTO> paymentMethod;
	
	private BigDecimal amount;
	
}
