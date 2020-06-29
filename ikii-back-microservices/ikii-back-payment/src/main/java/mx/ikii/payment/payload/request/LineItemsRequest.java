package mx.ikii.payment.payload.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class LineItemsRequest {
	
	private String name;
	private String description;
	private BigDecimal unit_price;
	private Integer quantity;
	private List<String> tags;
	private String type;

}
