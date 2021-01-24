package mx.ikii.commons.payload.response.general;

import lombok.Data;

@Data
public class JoinUsResponse {
  
  private String businessName;
  private String email;
  private String phone;
  private String message;

}
