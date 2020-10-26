package mx.ikii.payment.repository.custom;

import java.util.List;
import java.util.Optional;
import mx.ikii.commons.payload.dto.PaymentOrderDTO;
import mx.ikii.commons.payload.request.order.OrderFilter;

public interface IPaymentOrderRepositoryCustom {
  
  Optional<List<PaymentOrderDTO>> filter(OrderFilter orderFilter);

}
