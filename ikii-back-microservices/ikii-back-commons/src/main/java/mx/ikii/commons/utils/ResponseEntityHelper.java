package mx.ikii.commons.utils;

import org.springframework.http.ResponseEntity;

import mx.ikii.commons.exception.handler.GeneralException;
import mx.ikii.commons.exception.handler.ResourceNotFoundException;

/**
 * This class helps manage the request of the declarative FeignClient of spring cloud
 */
public class ResponseEntityHelper {

	/**
	 * 
	 * @param <T>            for any DTO response
	 * @param responseEntity represents the response entity obtained of the http
	 *                       request
	 * @return the plain response
	 */
	public static <T> T processingHttpStatus(ResponseEntity<T> responseEntity) {
		if (Nullable.isNull(responseEntity)) {
			return null;
		}
		switch (responseEntity.getStatusCode()) {
		case OK:
			return responseEntity.getBody();
		case CREATED:
			return responseEntity.getBody();
		case NOT_FOUND:
			throw new ResourceNotFoundException("Resource Not found");
		default:
			throw new GeneralException("Exception with status::" + responseEntity.getStatusCode());
		}
	}
}
