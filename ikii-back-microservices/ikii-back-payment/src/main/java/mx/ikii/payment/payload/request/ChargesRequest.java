package mx.ikii.payment.payload.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import mx.ikii.payment.payload.dto.PaymentMethodDTO;

@Data
public class ChargesRequest {
	private List<PaymentMethodDTO> paymentMethod;
	private BigDecimal amount;
}
