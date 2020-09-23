package mx.ikii.commons.persistence.collection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "PaymentOrder")
public class PaymentOrder {
	
	@Id
	private String id;
	private ObjectId customerId;
	private String customerProviderId;
	private String transactionId;
	private BigDecimal total;
	private BigDecimal suTotal;
	private BigDecimal providerComission;
	private BigDecimal internalComission;
	private BigDecimal tax;
	private BigDecimal amountRefunded;
	private Integer paymentMethod;
	private String status;
	private LocalDateTime createdAt;
	
	@DBRef
	private OrderDetail detail;	

}
