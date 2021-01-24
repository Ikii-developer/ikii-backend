package mx.ikii.commons.persistence.collection;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "JoinUs")
public class JoinUs {
  
  private String businessName;
  private String email;
  private String phone;
  private String message;

}
