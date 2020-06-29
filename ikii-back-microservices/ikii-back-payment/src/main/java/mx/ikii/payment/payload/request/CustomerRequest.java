package mx.ikii.payment.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class CustomerRequest {
	
	//Identificador único asignado al azar.
	private String id;
	
	
	private String name;
	
	private String email;
	
	//Teléfono del cliente (formato internacional).
	private String phone;
	
	//Arreglo de las tarjetas que ya se tokenizaron o están por ser guardadas.
	private List<PaymentSources> payment_sources;
	
	//id secundario de algún plan.
	private String plan_id;
	
	//Indica si un usuario es corporativo o no.
	//private Boolean corporate;
	
	/** Información de contacto el envío a cliente.
	 * https://developers.conekta.com/api?language=java#shipping-contact
	 */
	//private ShippingContacts shipping_contacts;
	
	/**
	 * Subscripciones a las que puede estar asociado el cliente. Ve a la sección de Suscripciones para más detalles.
	 */
	//private Subscriptions subscriptions;
	
	
	/**
	 * ##### static class #####
	 */
	
	@Data
	public static class PaymentSources {
		
		private String token_id;
		
		private String type;
		
	}

}
