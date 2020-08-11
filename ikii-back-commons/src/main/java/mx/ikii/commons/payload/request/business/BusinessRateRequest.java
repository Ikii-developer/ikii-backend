package mx.ikii.commons.payload.request.business;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BusinessRateRequest implements Serializable{
	
	private static final long serialVersionUID = -1339140897440540001L;
	private String id;
	private String businessId;
	private LocalDateTime updatedAt;
	private Double average;
	private List<RateRequest> rates;

}
