package mx.ikii.commons.payload.request.payment.conekta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerConektaWrapperRequest {

  private String ikiiCustomerId;
  private String ikiiCustomerDetailId;
  private ConektaCardInfo conektaCardInfo;

  @Builder
  @Data
  public static class ConektaCardInfo {

    private String type;
    private String token;

  }

}
