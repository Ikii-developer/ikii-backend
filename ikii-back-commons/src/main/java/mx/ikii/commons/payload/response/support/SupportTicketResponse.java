package mx.ikii.commons.payload.response.support;

import lombok.Data;
import mx.ikii.commons.domain.SupportTicketStatus;

@Data
public class SupportTicketResponse {

  private String id;
  private String customerId;
  private String bussinessId;
  private String orderId;
  private String detail;
  private SupportTicketStatus status;

}
