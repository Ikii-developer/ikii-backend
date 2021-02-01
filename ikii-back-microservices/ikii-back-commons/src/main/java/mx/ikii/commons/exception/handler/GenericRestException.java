package mx.ikii.commons.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * This class is used to map all the exceptions and turn them into a readable
 * user error
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
public class GenericRestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private @Getter @Setter HttpStatus httpStatus;

	private @Getter @Setter String message;

	private @Getter @Setter Integer error = null;

	private @Getter @Setter String errorDescription = null;

	public GenericRestException(String message, HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public GenericRestException(String message, HttpStatus httpStatus, Integer error) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.error = error;
	}

	public GenericRestException(String message, HttpStatus httpStatus, Integer error, String errorDescription) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public GenericRestException(String message, HttpStatus httpStatus, Integer error, String errorDescription,
			Throwable cause) {
		super(cause);
		this.httpStatus = httpStatus;
		this.message = message;
		this.error = error;
		this.errorDescription = errorDescription;
	}

	public GenericRestException(String message) {
		super();
		this.message = message;
	}

	public GenericRestException(Throwable cause, HttpStatus httpStatus) {
		super(cause);
		this.httpStatus = httpStatus;
	}

}
