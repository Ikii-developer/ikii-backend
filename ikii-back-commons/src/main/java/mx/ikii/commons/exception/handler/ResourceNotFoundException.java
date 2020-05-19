
package mx.ikii.commons.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This class is used to represent the Resource Not Found (404) http response
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends GenericRestException {

	private static final long serialVersionUID = 3592282281221214989L;

	private static final String MESSAGE = "Resource [%s]: %s not found";

	public ResourceNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}

	public ResourceNotFoundException(String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), HttpStatus.NOT_FOUND);
	}

	public ResourceNotFoundException(HttpStatus httpStatus, String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), httpStatus);
	}
}
