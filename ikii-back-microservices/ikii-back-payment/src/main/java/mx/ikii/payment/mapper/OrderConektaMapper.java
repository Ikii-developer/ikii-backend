package mx.ikii.payment.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.conekta.ConektaList;
import io.conekta.ConektaObject;
import io.conekta.Order;
import mx.ikii.payment.payload.dto.CustomerInfoDTO;
import mx.ikii.payment.payload.dto.LineItemsDTO;
import mx.ikii.payment.payload.response.OrderConektaResponse;

//TODO: CHANGE to Mapstruct
public class OrderConektaMapper {

	@SuppressWarnings("unchecked")
	public static OrderConektaResponse orderConektaToOrderConektaResponse(Order order) {

		OrderConektaResponse response = new OrderConektaResponse();
		response.setId(order.id);
		response.setAmount(BigDecimal.valueOf(order.amount)
				.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY));
		response.setPayment_status(order.payment_status);
		response.setCurrency(order.currency);

		CustomerInfoDTO customerInfoDTO = new CustomerInfoDTO();
		customerInfoDTO.setCustomer_id(order.customer_info.id);
		customerInfoDTO.setName(order.customer_info.name);
		customerInfoDTO.setEmail(order.customer_info.email);
		customerInfoDTO.setPhone(order.customer_info.phone);
		response.setCustomer_info(customerInfoDTO);
		
		List<LineItemsDTO> lineItemsDTO = new ArrayList<>();
		ConektaList lineItems = order.line_items;
		lineItems.forEach(item->{
			ConektaObject itemJSON = (ConektaObject) item;
			LineItemsDTO itemResponse = new LineItemsDTO();
			itemResponse.setId(itemJSON.getId());
			itemResponse.setName(itemJSON.getVal("name").toString());
			BigDecimal unitPrice = new BigDecimal(itemJSON.getVal("unit_price").toString())
					.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_UNNECESSARY);
			itemResponse.setUnit_price(unitPrice);
			itemResponse.setQuantity((Integer) itemJSON.getVal("quantity"));
			lineItemsDTO.add(itemResponse);
		});
		response.setLine_items(lineItemsDTO);
		response.setLine_itemsId(order.line_items.id);

		// ChargesDTO chargesDTO = new ChargesDTO();
		// response.setCharges(charges);
		response.setChargesId(order.charges.id);

		// response.setMetadata(order.metadata);
		
		return response;
	}

}
