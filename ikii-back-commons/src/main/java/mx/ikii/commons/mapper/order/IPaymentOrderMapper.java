package mx.ikii.commons.mapper.order;

import org.mapstruct.Mapper;

import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.response.payment.order.PaymentOrderResponse;
import mx.ikii.commons.persistence.collection.PaymentOrder;

@Mapper(componentModel = "spring", uses = { StringObjectIdMapper.class })
public interface IPaymentOrderMapper {

	PaymentOrderResponse entityToResponse(PaymentOrder entity);
	
}
