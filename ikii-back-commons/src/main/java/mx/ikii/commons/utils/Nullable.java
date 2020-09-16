package mx.ikii.commons.utils;

import java.util.Collection;
import java.util.Optional;

/**
 * This class handles the optional values returning a boolean and reducing a
 * some line of codes
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public final class Nullable {

	public static final boolean isNull(Object obj) {
		return !Optional.ofNullable(obj).isPresent();
	}

	public static final boolean isNotNull(Object obj) {
		return Optional.ofNullable(obj).isPresent();
	}

	@SuppressWarnings("rawtypes")
	public static final boolean isNullOrEmpty(Object obj) {

		if (!Optional.ofNullable(obj).isPresent()) {
			return true;
		}

		if (obj instanceof Collection) {
			if (((Collection) obj).isEmpty()) {
				return true;
			}
		} else if (obj instanceof Object[]) {
			if (((Object[]) obj).length == 0) {
				return true;
			}
		} else if(obj instanceof String) {
			if(((String) obj).isEmpty()) {
				return true;
			}
		}
		return false;
	}

}
