package mx.ikii.payment.service.ikii;

import mx.ikii.commons.persistence.collection.OrderDetail;

public interface IOrderDetailService {

	OrderDetail getById(String id);
	
	OrderDetail save(OrderDetail orderDetail);
	
	OrderDetail update(OrderDetail orderDetail);
	
}
