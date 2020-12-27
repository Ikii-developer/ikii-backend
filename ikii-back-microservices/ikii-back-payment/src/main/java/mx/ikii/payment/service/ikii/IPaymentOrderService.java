package mx.ikii.payment.service.ikii;

import java.util.List;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderFilter;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.commons.persistence.collection.PaymentOrder;

public interface IPaymentOrderService {

  PaymentOrder getById(String id);

  List<PaymentOrder> getByCustomerId(String id);

  PaymentOrder save(PaymentOrder paymentOrder);

  PaymentOrder update(PaymentOrder paymentOrder);

  void calculateTotals(PaymentOrder paymentOrder, OrderDetail orderDetail);

  List<PaymentOrderDTO> filter(OrderFilter orderFilter);

}
