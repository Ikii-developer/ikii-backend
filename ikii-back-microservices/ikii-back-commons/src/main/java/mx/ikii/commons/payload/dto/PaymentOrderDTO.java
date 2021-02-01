package mx.ikii.commons.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.ikii.commons.domain.OrderType;
import mx.ikii.commons.payload.response.payment.order.OrderDetailResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderDTO {
	
	@Id
	private String id;
	private ObjectId customerId;
	private String customerIdConekta;
	private String customerProviderId;
	private String transactionId;
	private BigDecimal total;
	private BigDecimal suTotal;
	private BigDecimal providerComission;
	private BigDecimal internalComission;
	private BigDecimal tax;
	private BigDecimal amountRefunded;
	private String status;
	private LocalDateTime createdAt;
	private String message;
	private PaymentMethodDTO paymentMethod;
	private String reasonRefund;
	private OrderType orderType;
	private OrderSubstatusDetail orderSubstatusDetail;
	private OrderDetailResponse detail;
	
	public String getCustomerId() {
      return this.customerId.toHexString();
    }
	
}
