package mx.ikii.commons.payload.request.business;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RateRequest implements Serializable{

	private static final long serialVersionUID = -2394394312728339451L;

	private String id;

	private String customerId;
	
	private String comment;
	
	private LocalDateTime createdAt;
	
	private Double starsRate;
	
}
