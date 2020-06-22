package mx.ikii.payment.service.order;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.conekta.Order;
import mx.ikii.payment.conekta.service.orders.IOrdersConektaService;
import mx.ikii.payment.payload.dto.CustomerInfoDTO;
import mx.ikii.payment.payload.request.OrderConektaRequest;
import mx.ikii.payment.payload.response.OrderConektaResponse;

@Service
public class IkiiPaymentOrderServiceWrapperImpl implements IKiiPaymentOrderServiceWrapper {
	
//	@Autowired
//	private IOrderOpenPayService orderOpenPayService;
	
	@Autowired
	private IOrdersConektaService ordersConectaService;

	@Override
	public OrderConektaResponse createOrderWithCardCharge(OrderConektaRequest orderConektaRequest) {
		
		Order order = ordersConectaService.createOrderWithCardCharge(orderConektaRequest);
		
		// TODO:  CHANGE to Mapper
		OrderConektaResponse response = new OrderConektaResponse();
		response.setId(order.id);
		response.setAmount(BigDecimal.valueOf(order.amount));
		response.setPayment_status(order.payment_status);
		
		CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
		customerInfoDTO.setCustomer_id(order.customer_info.id);
		customerInfoDTO.setName(order.customer_info.name);
		customerInfoDTO.setEmail(order.customer_info.email);
		customerInfoDTO.setPhone(order.customer_info.phone);
		response.setCustomer_info(customerInfoDTO);
		
		//List<LineItemsDTO> lineItemsDTO = new ArrayList<>();
		//response.setLine_items(lineItemsDTO);
		
		response.setLine_itemsId(order.line_items.id);
		
		//ChargesDTO chargesDTO = new ChargesDTO();
		//response.setCharges(charges);
		response.setChargesId(order.charges.id);
		
		//response.setMetadata(order.metadata);
		
		return response;
	}

	@Override
	public OrderConektaResponse updateOrderConekta(OrderConektaRequest orderConektaRequest) {
		Order order = ordersConectaService.updateOrder(orderConektaRequest);
		
		// TODO:  CHANGE to Mapper
		OrderConektaResponse response = new OrderConektaResponse();
		response.setId(order.id);
		response.setAmount(BigDecimal.valueOf(order.amount));
		response.setPayment_status(order.payment_status);
		
		CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
		customerInfoDTO.setCustomer_id(order.customer_info.id);
		customerInfoDTO.setName(order.customer_info.name);
		customerInfoDTO.setEmail(order.customer_info.email);
		customerInfoDTO.setPhone(order.customer_info.phone);
		response.setCustomer_info(customerInfoDTO);
		
		//List<LineItemsDTO> lineItemsDTO = new ArrayList<>();
		//response.setLine_items(lineItemsDTO);
		response.setLine_itemsId(order.line_items.id);
		
		//ChargesDTO chargesDTO = new ChargesDTO();
		//response.setCharges(charges);
		response.setChargesId(order.charges.id);
		
		//response.setMetadata(order.metadata);
		
		return response;
	}

	@Override
	public OrderConektaResponse findOrderConekta(String orderId) {
		Order order = ordersConectaService.findOrder(orderId);
		
		// TODO:  CHANGE to Mapper
		OrderConektaResponse response = new OrderConektaResponse();
		response.setId(order.id);
		response.setAmount(BigDecimal.valueOf(order.amount));
		response.setPayment_status(order.payment_status);
		
		CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
		customerInfoDTO.setCustomer_id(order.customer_info.id);
		customerInfoDTO.setName(order.customer_info.name);
		customerInfoDTO.setEmail(order.customer_info.email);
		customerInfoDTO.setPhone(order.customer_info.phone);
		response.setCustomer_info(customerInfoDTO);
		
		//List<LineItemsDTO> lineItemsDTO = new ArrayList<>();
		//response.setLine_items(lineItemsDTO);
		response.setLine_itemsId(order.line_items.id);
		
		//ChargesDTO chargesDTO = new ChargesDTO();
		//response.setCharges(charges);
		response.setChargesId(order.charges.id);
		
		//response.setMetadata(order.metadata);
		
		return response;
	}
	
	
	
	

}
