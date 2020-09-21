package mx.ikii.commons.payload.response.payment.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentOrderResponse {
	
	private String id;
	private String customerId;
	private String customerProviderId;
	private String transactionId;
	private BigDecimal total;
	private BigDecimal suTotal;
	private BigDecimal providerComission;
	private BigDecimal internalComission;
	private BigDecimal tax;
	private BigDecimal amountRefunded;
	private Integer paymentMethod;
	private String status;
	private LocalDateTime createdAt;
	private OrderDetailResponse detail;	

}
