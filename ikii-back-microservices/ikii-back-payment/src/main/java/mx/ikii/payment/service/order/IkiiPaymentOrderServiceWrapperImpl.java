package mx.ikii.payment.service.order;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Order;
import mx.ikii.payment.mapper.OrderConektaMapper;
import mx.ikii.payment.methods.conekta.service.orders.IOrdersConektaService;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.request.RefoundOrderRequest;
import mx.ikii.payment.payload.response.OrderConektaResponse;

@Service
public class IkiiPaymentOrderServiceWrapperImpl implements IKiiPaymentOrderServiceWrapper {
	
//	@Autowired
//	private IOrderOpenPayService orderOpenPayService;
	
	@Autowired
	private IOrdersConektaService ordersConectaService;

	@Override
	public OrderConektaResponse createOrderWithCardCharge(OrderConektaRequest orderConektaRequest) {
		orderConektaRequest.getLine_items().forEach(c-> {
			if(new BigDecimal(5000).compareTo(c.getUnit_price()) == -1) {
				throw new RuntimeException("You're probably a hacker");
			}
		});
		
		Order order = ordersConectaService.createOrderWithCardCharge(orderConektaRequest);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		return response;
	}

	@Override
	public OrderConektaResponse updateOrderConekta(OrderConektaRequest orderConektaRequest) {
//		orderConektaRequest.getLine_items().forEach(c-> {
//			if(new BigDecimal(5000).compareTo(c.getUnit_price()) == -1) {
//				throw new RuntimeException("You're probably a hacker");
//			}
//		});
		
		Order order = ordersConectaService.updateOrder(orderConektaRequest);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		return response;
	}

	@Override
	public OrderConektaResponse findOrderConekta(String orderId) {
		Order order = ordersConectaService.findOrder(orderId);
		OrderConektaResponse response = OrderConektaMapper.orderConektaToOrderConektaResponse(order);
		return response;
	}
	
	@Override
	public void orderAuthorizeCapture(String orderId) {
		//Procesa una orden que ha sido previamente autorizada.
		ordersConectaService.captureOrder(orderId);
	}

	@Override
	public void refoundOrderConekta(RefoundOrderRequest refoundOrderRequest) {
		ordersConectaService.refoundOrder(refoundOrderRequest);
	}

}
