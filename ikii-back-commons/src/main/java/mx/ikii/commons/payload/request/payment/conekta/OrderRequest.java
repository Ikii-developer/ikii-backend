package mx.ikii.commons.payload.request.payment.conekta;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class OrderRequest {
	
	/**
	 * Divisa con la cual se realizará el cobro. 
	 * Utiliza el código de 3 letras del Estándar Internacional ISO 4217.
	 */
	private String currency;
	
	private Map<String, String> metadata;
	
	/**
	 * Lista de los productos que se venden en la orden. 
	 * Debe tener al menos un producto.
	 */
	private List<LineItemsRequest> line_items;
	
	/**
	 * Lista de los costos de envío. 
	 * Si la tienda en línea ofrece productos digitales, este parámetro es opcional.
	 * https://developers.conekta.com/api?language=java#shipping-line
	 */
	//private List<ShippingLine> shipping_lines;
	
	/**
	 * Lista de los impuestos que se pagan.
	 * https://developers.conekta.com/api?language=java#tax-line
	 */
	//private TaxLine taxLine;
	
	/**
	 * Lista de los descuentos que se aplican a la orden.
	 * https://developers.conekta.com/api?language=java#discount-line
	 */
	//private List<DiscountLines> discount_lines;
	
	/**
	 * Indica si los cargos de la orden deben ser preautorizados.
	 */
	//private Boolean pre_authorize;
	
	/**
	 * Hash que contiene la información del cliente.
	 */
	private CustomerInfoRequest customer_info;
	
	/** hash
	 * Detalles del envío, obligatorio en caso de mandar un shipping_line. 
	 * Si no recibimos un shipping_contact en la orden, se utilizará el shipping_contact del customer por default.
	 */
	//private ShippingContact shipping_contact;
	
	/**
	 * Lista de los cargos que se generaron para cubrir el monto de la orden.
	 */
	private ChargesRequest charges;
	
}
