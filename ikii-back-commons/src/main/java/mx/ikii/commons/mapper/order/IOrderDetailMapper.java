package mx.ikii.commons.mapper.order;

import java.util.List;
import org.mapstruct.Mapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.response.payment.order.OrderDetailResponse;
import mx.ikii.commons.persistence.collection.OrderDetail;

@Mapper(componentModel = "spring", uses = {StringObjectIdMapper.class})
public interface IOrderDetailMapper {

  OrderDetailResponse entityToResponse(OrderDetail entity);

  List<OrderDetailResponse> entityToResponse(List<OrderDetail> entity);

}
