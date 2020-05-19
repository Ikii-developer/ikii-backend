package mx.ikii.users.helper;

import mx.ikii.commons.persistence.collection.UserClip;
import mx.ikii.commons.utils.Nullable;

/**
 * This class helps the user business logic decoupling the code
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public class UserClipHelper {

	/**
	 * This method is used to update the instance variables of a existing entity
	 * with the instance variables of a new entity
	 * 
	 * @param entity    existing entity
	 * @param newEntity new entity
	 */
	public static void setUpdateProperties(UserClip entity, UserClip newEntity) {

		if (Nullable.isNotNull(newEntity)) {

			if (Nullable.isNotNull(newEntity.getRoles())) {
				entity.setRoles(newEntity.getRoles());
			}
			if (Nullable.isNotNull(newEntity.getUserName())) {
				entity.setUserName(newEntity.getUserName());
			}
			if (Nullable.isNotNull(newEntity.getPassword())) {
				entity.setPassword(newEntity.getPassword());
			}
		}

	}

}
