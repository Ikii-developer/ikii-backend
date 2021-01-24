
package mx.ikii.commons.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * https://developers.conekta.com/api#errors
 *
 * api_error: Error inesperado por parte de Conekta. Este error puede ser capturado con la clase `ApiError` .
 * authentication_error: La llave usado en esta petición era inválida or no tiene permisos para ejecutar esta petición. 
 * 			Este error puede ser capturado con la clase `AuthenticationError`.
 * conlict_error: Un conflict de versiones ocurrió, lo más probable es que la versión que pasaste en las encabezados de la petición era incorrecta.
 * malformed_request_error: El JSON del cuerpo de tu petición era inválida o la codificación de caracteres en tu petición es incorrecta. 
 * 			Este error puede ser capturado con la clase `MalFormedRequestError` .
 * parameter_validation_error: Algun parámetro de tu petición falta o tiene valores inválidos, 
 * 				consulta el atributo `details` en tu petición para ver cuales campos fueron inválidos y por qué. 
 * 			Este error puede ser capturado con la clase `ParameterValidationError` .
 * precondition_required_error: Falta ejecutar algunos pasos antes de procesar esta llamada. 
 * 			Por ejemplo, si estas creando un `order`, la suma de los `line_items` tiene que estar mayor a 0 antes de cobrarle, 
 * 				consulta el atributo `details` en tu petición para ver cuales campos fueron inválidos y por qué.
 * processing_error: Un error rutinario durante el procesamiento de tarjetas, ocurre cuando procesen tarjetas y los fondos no pueden ser cobrados. 
 * 			Este error puede ser capturado con la clase `ProcessingError` .
 * resource_not_found_error: El objecto sobre cual quisieras ejecutar este petición no existe. 
 * 			Ocurre normalmente cuando las llaves de la petición corresponden a otra cuenta o no tienen permisos para buscar este objecto. 
 * 			Este error puede ser capturado con la clase `ResourceNotFoundError` .
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConektaRepositoryException extends GenericRestException {

	private static final long serialVersionUID = 3592282281221214989L;

	private static final String MESSAGE = "Resource Repository [%s]: %s has a conflict";

	public ConektaRepositoryException(String message) {
		super(message, HttpStatus.CONFLICT);
	}

	public ConektaRepositoryException(String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), HttpStatus.CONFLICT);
	}

	public ConektaRepositoryException(HttpStatus httpStatus, String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), httpStatus);
	}
}
