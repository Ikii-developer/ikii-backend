
package mx.ikii.commons.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends GenericRestException {

	private static final long serialVersionUID = -211664380901549148L;

	public GeneralException(String message) {
		super(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public GeneralException(String message, HttpStatus httpStatus) {
		super(message, httpStatus);
	}

}
