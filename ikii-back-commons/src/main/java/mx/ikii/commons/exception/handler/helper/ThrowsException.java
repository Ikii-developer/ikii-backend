package mx.ikii.commons.exception.handler.helper;

import java.util.Optional;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;

/**
 * This class helps the general exception business management
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
public class ThrowsException {
	/**
	 * This method validates if the wrapping optional contains the content
	 * 
	 * @param <T>        to any Class
	 * @param resource   optional to be validated
	 * @param resourceId id of the underlying resource
	 * @param clazz      representing the resource Class
	 * @return the content of the optional resource or a resourceNotFound exception
	 *         in case it is not present
	 */
	public static <T> T resourceNotFound(Optional<T> resource, String resourceId, Class<T> clazz) {

		throw new ResourceNotFoundException(resourceId, clazz);

	}

}
