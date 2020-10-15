package mx.ikii.commons.payload.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import mx.ikii.commons.domain.OrderSubStatus;

@Data
public class OrderSubstatusDetail {

	public OrderSubStatus subStatus;
	public LocalDateTime date = LocalDateTime.now();
	public String description;
	public List<OrderSubstatusDetail> subStatusHistory;
	
	public OrderSubstatusDetail(OrderSubStatus subStatus, LocalDateTime date) {
		super();
		this.subStatus = subStatus;
		this.date = date;
	}

	public OrderSubstatusDetail(OrderSubStatus subStatus, LocalDateTime date, String description) {
		super();
		this.subStatus = subStatus;
		this.date = date;
		this.description = description;
	}
}
