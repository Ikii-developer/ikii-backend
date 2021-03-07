package mx.ikii.commons.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends GenericRestException {

  private static final long serialVersionUID = 3592282281221214989L;

  private static final String MESSAGE = "Resource [%s]: %s not found";

  public BadRequestException(String message) {
    super(message, HttpStatus.NOT_FOUND);
  }

  public BadRequestException(String resourceId, Class<?> clazz) {
    super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), HttpStatus.NOT_FOUND);
  }

  public BadRequestException(HttpStatus httpStatus, String resourceId, Class<?> clazz) {
    super(String.format(MESSAGE, clazz.getSimpleName(), resourceId), httpStatus);
  }
}
