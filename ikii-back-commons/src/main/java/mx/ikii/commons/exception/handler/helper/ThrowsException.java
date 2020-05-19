package mx.ikii.commons.exception.handler.helper;

import java.util.Optional;

import mx.ikii.commons.exception.handler.ResourceNotFoundException;
import mx.ikii.commons.utils.Nullable;

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

		if (Nullable.isNull(resource) || !resource.isPresent()) {
			throw new ResourceNotFoundException(resourceId, clazz);
		}

		return clazz.cast(resource.get());
	}

	/**
	 * 
	 * @param <T>        to any Class
	 * @param resource   resource to be validated
	 * @param resourceId id of the underlying resource
	 * @return the resource or a resourceNotFound exception in case is null
	 */
	public static <T> T resourceNotFound(T resource, String resourceId) {

		if (Nullable.isNull(resource)) {
			throw new ResourceNotFoundException(resourceId);
		}

		return resource;
	}
}
