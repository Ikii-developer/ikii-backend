package mx.ikii.commons.exception.handler.helper;

import java.util.Optional;

import mx.ikii.commons.utils.Nullable;

public class RepositoryHelper {

	public static <T> T validateOptional(Optional<T> resource, String resourceId, Class<T> clazz) {

		if (Nullable.isNull(resource) || !resource.isPresent()) {
			ThrowsException.resourceNotFound(resource, resourceId, clazz);
		}

		return clazz.cast(resource.get());
	}
	
}
