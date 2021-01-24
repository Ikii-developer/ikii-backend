package mx.ikii.commons.payload.request.payment.conekta;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import mx.ikii.commons.payload.dto.PaymentMethodDTO;

@Data
public class ChargesRequest {
	private List<PaymentMethodDTO> paymentMethod;
}
