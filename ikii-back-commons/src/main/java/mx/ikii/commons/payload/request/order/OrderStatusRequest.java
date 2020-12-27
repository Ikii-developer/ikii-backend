package mx.ikii.commons.payload.request.order;

import lombok.Data;
import mx.ikii.commons.domain.OrderSubStatus;

@Data
public class OrderStatusRequest {

	private OrderSubStatus subStatus;
	private String description;
	private String value;

}
