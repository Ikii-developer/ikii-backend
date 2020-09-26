package mx.ikii.payment.payload.request;

import java.util.List;
import java.util.Map;

import lombok.Data;
import mx.ikii.payment.payload.dto.ChargesDTO;
import mx.ikii.payment.payload.dto.CustomerInfoDTO;
import mx.ikii.payment.payload.dto.LineItemsDTO;
import mx.ikii.payment.payload.dto.TaxLineDTO;

/**
 * Una orden representa una compra. 
 * Incluye todos los detalles relacionados a ella, como método de pago, envío, productos comprados, cargos, descuentos e impuestos.
 * 
 * id: El id de la orden.
 * line_items: Lista de los productos que se venden en la orden. Debe tener al menos un producto.
 * shipping_lines: Lista de los costos de envío. Si la tienda en línea ofrece productos digitales, este parámetro es opcional.
 * tax_lines: Lista de los impuestos que se pagan.
 * discount_lines: Lista de los descuentos que se aplican a la orden.
 * pre_authorize: Indica si los cargos de la orden deben ser preautorizados.
 * customer_info: Hash que contiene la información del cliente.
 * shipping_contact: Detalles del envío, obligatorio en caso de mandar un shipping_line. 
 * 			Si no recibimos un shipping_contact en la orden, se utilizará el shipping_contact del customer por default.
 * charges: Lista de los cargos que se generaron para cubrir el monto de la orden.
*/
@Data
public class OrderConektaRequest {
	
	private String order_id;
	
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
	private List<LineItemsDTO> line_items;
	
	/**
	 * Hash que contiene la información del cliente.
	 */
	private CustomerInfoDTO customer_info;
	
	/**
	 * Lista de los cargos que se generaron para cubrir el monto de la orden.
	 */
	private List<ChargesDTO> charges;
	
	
	/**
	 * Lista de los costos de envío. 
	 * Si la tienda en línea ofrece productos digitales, este parámetro es opcional.
	 * https://developers.conekta.com/api?language=java#shipping-line
	 */
	//private List<ShippingLine> shipping_lines;
	
	/** hash
	 * Detalles del envío, obligatorio en caso de mandar un shipping_line. 
	 * Si no recibimos un shipping_contact en la orden, se utilizará el shipping_contact del customer por default.
	 */
	//private ShippingContact shipping_contact;
	
	/**
	 * Lista de los impuestos que se pagan.
	 * https://developers.conekta.com/api?language=java#tax-line
	 */
	private List<TaxLineDTO> tax_lines;
	
	/**
	 * Lista de los descuentos que se aplican a la orden.
	 * https://developers.conekta.com/api?language=java#discount-line
	 */
	//private List<DiscountLines> discount_lines;
	
	/**
	 * Indica si los cargos de la orden deben ser preautorizados.
	 */
	//private Boolean pre_authorize;
	
}
