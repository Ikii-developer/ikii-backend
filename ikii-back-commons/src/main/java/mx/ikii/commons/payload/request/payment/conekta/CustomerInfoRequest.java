package mx.ikii.commons.payload.request.payment.conekta;

import lombok.Data;

/**
 * customer_id: ID del cliente. Es obligatorio cuando no se envían los otros campos de `customer_info`.
 * name: Nombre del cliente. (opcional si se envía el `id`)
 * phone: Teléfono del cliente. (opcional si se envía el `id`)
 * email: Email del cliente. (opcional si se envía el `id`)
 * corporate: Booleano que indica si el usuario es corporativo o no, el default es `false`. (opcional)
 */
@Data
public class CustomerInfoRequest {

	private String name;
	private String phone;
	private String email;
	
}
