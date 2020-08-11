package mx.ikii.commons.payload.response.business;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class BusinessRateResponse {
	
	private String id;
	private String businessId;
	private LocalDateTime updatedAt;
	private Double average;
	private List<RateResponse> rates;


}
