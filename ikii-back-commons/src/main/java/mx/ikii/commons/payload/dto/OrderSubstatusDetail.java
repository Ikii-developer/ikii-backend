package mx.ikii.commons.payload.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import mx.ikii.commons.domain.OrderSubStatus;

@Builder
@Data
public class OrderSubstatusDetail {

  private OrderSubStatus subStatus;
  private LocalDateTime date;
  private String description;
  private List<OrderSubstatusDetail> subStatusHistory;

  public OrderSubstatusDetail() {
    super();
  }

  public OrderSubstatusDetail(OrderSubStatus subStatus, LocalDateTime date) {
    super();
    this.subStatus = subStatus;
    this.date = date;
  }

  public OrderSubstatusDetail(OrderSubStatus subStatus, LocalDateTime date, String description) {
    super();
    this.subStatus = subStatus;
    this.date = date;
    this.description = description;
  }


  public OrderSubstatusDetail(OrderSubStatus subStatus, LocalDateTime date, String description,
      List<OrderSubstatusDetail> subStatusHistory) {
    super();
    this.subStatus = subStatus;
    this.date = date;
    this.description = description;
    this.subStatusHistory = subStatusHistory;
  }


}
