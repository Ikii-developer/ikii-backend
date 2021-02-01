package mx.ikii.commons.persistence.collection;

import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import mx.ikii.commons.domain.SupportTicketStatus;

@Data
@Document(collection = "SupportTicket")
public class SupportTicket {

  private String id;
  private ObjectId customerId;
  private ObjectId bussinessId;
  private ObjectId orderId;
  private String detail;
  private SupportTicketStatus status;
  private LocalDateTime createdAt;
  
}
