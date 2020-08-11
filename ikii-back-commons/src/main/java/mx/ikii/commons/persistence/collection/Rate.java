package mx.ikii.commons.persistence.collection;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class Rate {
	
	private String id;

	private ObjectId customerId;
	
	private String comment;
	
	private LocalDateTime createdAt;
	
	private Double starsRate;

}