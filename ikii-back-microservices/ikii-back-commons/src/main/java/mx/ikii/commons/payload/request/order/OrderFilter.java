package mx.ikii.commons.payload.request.order;

import lombok.Data;

@Data
public class OrderFilter {
  
  private String businessId;
  private String customerId;
  private String status;

}
