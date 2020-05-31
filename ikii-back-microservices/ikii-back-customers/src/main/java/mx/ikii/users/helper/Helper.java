package mx.ikii.users.helper;

import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.utils.Nullable;

/**
 * This class helps the user business logic decoupling the code
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public class Helper {

	/**
	 * This method is used to update the instance variables of a existing entity
	 * with the instance variables of a new entity
	 * 
	 * @param entity    existing entity
	 * @param newEntity new entity
	 */
	public static void setUpdateProperties(Customer entity, Customer newEntity) {

		if (Nullable.isNotNull(newEntity)) {

			if (Nullable.isNotNull(newEntity.getRoles())) {
				entity.setRoles(newEntity.getRoles());
			}
			if (Nullable.isNotNull(newEntity.getEmail())) {
				entity.setEmail(newEntity.getEmail());
			}
			if (Nullable.isNotNull(newEntity.getPhoneNumber())) {
				entity.setPhoneNumber(newEntity.getPhoneNumber());
			}
			if (Nullable.isNotNull(newEntity.getPassword())) {
				entity.setPassword(newEntity.getPassword());
			}
			if (Nullable.isNotNull(newEntity.getIsEnabled())) {
				entity.setIsEnabled(newEntity.getIsEnabled());
			}
			if (Nullable.isNotNull(newEntity.getName())) {
				entity.setName(newEntity.getName());
			}
			if (Nullable.isNotNull(newEntity.getLastName())) {
				entity.setLastName(newEntity.getLastName());
			}
			if (Nullable.isNotNull(newEntity.getSecondLastName())) {
				entity.setSecondLastName(newEntity.getSecondLastName());
			}
		}

	}

}
