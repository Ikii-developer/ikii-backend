package mx.ikii.commons.exception.handler.helper;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import mx.ikii.commons.exception.handler.GenericRestException;
import mx.ikii.commons.utils.constants.EnumError;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends GenericRestException{

	private static final long serialVersionUID = -1259898907075222566L;
	private static final String MESSAGE = "Conflict";

	public ConflictException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
	
	public ConflictException(EnumError message) {
		super(message.toString(), HttpStatus.CONFLICT);
	}

	public ConflictException(String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), HttpStatus.CONFLICT);
	}

	public ConflictException(HttpStatus httpStatus, String resourceId, Class<?> clazz) {
		super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), httpStatus);
	}
	
}
