package mx.ikii.commons.payload.response.business;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RateResponse {
	
	private String id;
	private String customerId;
	private String comment;
	private LocalDateTime createdAt;
	private Double starsRate;

}
