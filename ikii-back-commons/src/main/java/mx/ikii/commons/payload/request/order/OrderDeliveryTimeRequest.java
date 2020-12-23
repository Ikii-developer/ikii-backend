package mx.ikii.commons.payload.request.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDeliveryTimeRequest {
  
  private Integer minutesToArrive;
}
