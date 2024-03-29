package mx.ikii.commons.payload.request.order;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import mx.ikii.commons.domain.OrderType;
import mx.ikii.commons.domain.PaymentType;
import mx.ikii.commons.payload.dto.PaymentMethodDTO;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderRequest {
	
	private String idOrder;	
	private String costumerId;
	private String businessId;
	private BigDecimal total;
	private BigDecimal subTotal;
	private List<OrderDetailRequest> orderDetails;
	private PaymentMethodDTO paymentMethod;
	private String status;
	private String reasonRefund;
	private OrderType orderType;
	private PaymentType paymentType;
	private Boolean accounted;

}
