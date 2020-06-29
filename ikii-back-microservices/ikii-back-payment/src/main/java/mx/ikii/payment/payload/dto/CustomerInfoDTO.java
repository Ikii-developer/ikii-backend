package mx.ikii.payment.payload.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * Contiene la información del cliente.
 * 
 * customer_id: ID del cliente. Es obligatorio cuando no se envían los otros campos de `customer_info`.
 * name: Nombre del cliente. (opcional si se envía el `id`)
 * phone: Teléfono del cliente. (opcional si se envía el `id`)
 * email: Email del cliente. (opcional si se envía el `id`)
 * corporate: Booleano que indica si el usuario es corporativo o no, el default es `false`. (opcional)
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfoDTO {
	
	private String customer_id;
	
	private String name;
	
	private String phone;
	
	private String email;
	
	private List<LineItemsDTO> line_items;
	
}
