package mx.ikii.payment.service.ikii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.persistence.collection.OrderDetail;
import mx.ikii.payment.repository.IOrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

	@Autowired
	private IOrderDetailRepository orderDetailRepositroy;

	@Override
	public OrderDetail getById(String id) {
		return orderDetailRepositroy.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(id, OrderDetail.class));
	}

	@Override
	public OrderDetail save(OrderDetail orderDetail) {
		return orderDetailRepositroy.insert(orderDetail);
	}

	@Override
	public OrderDetail update(OrderDetail orderDetail) {
		return orderDetailRepositroy.save(orderDetail);
	}

	@Override
	public void delete(String id) {
		 OrderDetail persistedOrderDetail = this.getById(id);
		 orderDetailRepositroy.delete(persistedOrderDetail);
	}

}
