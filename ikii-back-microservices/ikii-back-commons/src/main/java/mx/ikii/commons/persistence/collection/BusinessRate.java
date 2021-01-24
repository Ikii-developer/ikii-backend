package mx.ikii.commons.persistence.collection;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Data
@Document(collection = "BusinessRate")
public class BusinessRate {

	@Id
	private String id;
	private ObjectId businessId;
	private LocalDateTime updatedAt;
	private Double average;
	private List<Rate> rates;

}
