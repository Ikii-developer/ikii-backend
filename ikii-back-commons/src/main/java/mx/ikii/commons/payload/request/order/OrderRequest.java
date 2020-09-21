package mx.ikii.commons.payload.request.order;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class OrderRequest {
	
	private String idOrder;	
	private String costumerId;
	private String businessId;
	private BigDecimal total;
	private BigDecimal subTotal;
	private List<OrderDetailRequest> orderDetails;

}
