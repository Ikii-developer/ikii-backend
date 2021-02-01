package mx.ikii.commons.persistence.collection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.ikii.commons.domain.OrderType;
import mx.ikii.commons.domain.PaymentType;
import mx.ikii.commons.payload.dto.OrderSubstatusDetail;
import mx.ikii.commons.payload.dto.PaymentMethodDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "PaymentOrder")
public class PaymentOrder {

  @Id
  private String id;
  private ObjectId customerId;
  private String customerIdConekta;
  private String customerProviderId;
  private String transactionId;
  private Integer orderNumber;
  private BigDecimal total;
  private BigDecimal suTotal;
  private BigDecimal providerComission;
  private BigDecimal internalComission;
  private BigDecimal tax;
  private BigDecimal amountRefunded;
  private String status;
  private LocalDateTime createdAt;
  private LocalDateTime arrivedTime;
  private String message;
  private PaymentMethodDTO paymentMethod;
  private String reasonRefund;
  private OrderSubstatusDetail orderSubstatusDetail;
  private OrderType orderType;
  private PaymentType paymentType;
  private Boolean accounted;
  @DBRef
  private OrderDetail orderDetail;


}
