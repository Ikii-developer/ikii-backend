package mx.ikii.commons.persistence.collection;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * This class is used to maintain information
 * about customer transaction 
 *
 */
@Data
@Document
public class PaymentOrderInfo {
	
	private String id;
	
	private ObjectId customerIkiiId;
	
	private String customerConektaId;
	
	private String orderConektaId;
	
	private String paymentStatus;
	
	private String paymentMethodId;

}
