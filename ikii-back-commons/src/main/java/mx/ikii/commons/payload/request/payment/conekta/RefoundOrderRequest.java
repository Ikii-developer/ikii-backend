package mx.ikii.commons.payload.request.payment.conekta;

import java.math.BigDecimal;

import lombok.Data;

/** Reembolsar Orden
 * Una orden reembolsada describe los articulos, monto y razón por la que una orden está siendo reembolsada.
 */
@Data
public class RefoundOrderRequest {
	
	private String order_id;
	
	/**		Reason
	 * 
	 * requested_by_client
	 * cannot_be_fulfilled
	 * duplicated_transaction
	 * suspected_fraud
	 * other
	 */
	private String reason; // Change to ENUM
	
	/**
	 * amount (Opcional) Monto a reembolsar en caso de reembolso parcial.
	 */
	private BigDecimal amount;

}
