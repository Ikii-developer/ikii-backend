package mx.ikii.commons.mapper.order;

import java.util.List;
import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.commons.persistence.collection.PaymentOrder;

@Mapper(componentModel = "spring", uses = {StringObjectIdMapper.class, IOrderDetailMapper.class})
public interface IPaymentOrderMapper {

  PaymentOrderResponse entityToResponse(PaymentOrder entity);

  List<PaymentOrderResponse> entityToResponse(List<PaymentOrder> entity);


}
