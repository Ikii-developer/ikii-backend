package mx.ikii.commons.payload.response.payment.conekta;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import mx.ikii.commons.payload.dto.ChargesDTO;
import mx.ikii.commons.payload.dto.CustomerInfoDTO;
import mx.ikii.commons.payload.dto.LineItemsDTO;
import mx.ikii.commons.payload.dto.TaxLineDTO;

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
	
	private List<TaxLineDTO> tax_lines;

	private Map<String,String> metadata;
	
}
