package mx.ikii.payment.payload.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import mx.ikii.payment.payload.dto.ChargesDTO;
import mx.ikii.payment.payload.dto.CustomerInfoDTO;
import mx.ikii.payment.payload.dto.LineItemsDTO;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderConektaResponse {
	
	private String id;
	
	private BigDecimal amount;
	
	private BigDecimal amount_refunded;
	
	private String payment_status;
	
	private String currency;
	
	private CustomerInfoDTO customer_info;
	
	private String line_itemsId;
	private List<LineItemsDTO> line_items;
	
	private String chargesId;
	private List<ChargesDTO> charges;

	private Map<String,String> metadata;
	
}