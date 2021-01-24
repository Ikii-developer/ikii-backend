package mx.ikii.commons.payload.request.general;

import lombok.Data;

@Data
public class JoinUsRequest {
  
  private String businessName;
  private String email;
  private String phone;
  private String message;

}
