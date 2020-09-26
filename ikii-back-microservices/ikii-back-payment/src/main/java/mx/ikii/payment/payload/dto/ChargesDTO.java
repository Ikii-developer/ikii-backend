package mx.ikii.payment.payload.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import mx.ikii.commons.payload.dto.PaymentMethodDTO;

/**
 * Lista de los cargos que se generaron para cubrir el monto de la orden.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChargesDTO {
	
	private String id;
	
	private PaymentMethodDTO payment_method;
	
	private Integer amount;
	
}
