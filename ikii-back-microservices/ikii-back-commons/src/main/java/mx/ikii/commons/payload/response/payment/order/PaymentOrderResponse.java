package mx.ikii.commons.payload.response.payment.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import mx.ikii.commons.domain.OrderType;
import mx.ikii.commons.domain.PaymentType;
import mx.ikii.commons.payload.dto.OrderSubstatusDetail;
import mx.ikii.commons.payload.dto.PaymentMethodDTO;
import mx.ikii.commons.payload.response.payment.conekta.OrderConektaResponse;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentOrderResponse {

  private String id;
  private String customerId;
  private String customerProviderId;
  private String transactionId;
  private Integer orderNumber;
  private BigDecimal total;
  private BigDecimal suTotal;
  private BigDecimal providerComission;
  private BigDecimal internalComission;
  private BigDecimal tax;
  private BigDecimal amountRefunded;
  private PaymentMethodDTO paymentMethod;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime arrivedTime;
  private String message;
  private OrderDetailResponse orderDetail;
  private OrderConektaResponse orderConektaResponse;
  private String reasonRefund;
  private OrderSubstatusDetail orderSubstatusDetail;
  private OrderType orderType;
  private PaymentType paymentType;
  private Boolean accounted;

}