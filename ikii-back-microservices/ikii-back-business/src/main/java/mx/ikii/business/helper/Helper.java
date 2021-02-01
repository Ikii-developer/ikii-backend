package mx.ikii.business.helper;

import mx.ikii.commons.persistence.collection.Business;
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
	public static void setUpdateProperties(Business entity, Business newEntity) {

		if (Nullable.isNotNull(newEntity)) {

			if (Nullable.isNotNull(newEntity.getCloseTime())) {
				entity.setCloseTime(newEntity.getCloseTime());
			}
			if (Nullable.isNotNull(newEntity.getCustomerId())) {
				entity.setCustomerId(newEntity.getCustomerId());
			}
			if (Nullable.isNotNull(newEntity.getDeliveryTime())) {
				entity.setDeliveryTime(newEntity.getDeliveryTime());
			}
			if (Nullable.isNotNull(newEntity.getDescription())) {
				entity.setDescription(newEntity.getDescription());
			}
			if (Nullable.isNotNull(newEntity.getCategoryId())) {
				entity.setCategoryId(newEntity.getCategoryId());
			}
			if (Nullable.isNotNull(newEntity.getImage())) {
				entity.setImage(newEntity.getImage());
			}
			if (Nullable.isNotNull(newEntity.getIsOpen())) {
				entity.setIsOpen(newEntity.getIsOpen());
			}
			if (Nullable.isNotNull(newEntity.getName())) {
				entity.setName(newEntity.getName());
			}
			if (Nullable.isNotNull(newEntity.getStatus())) {
				entity.setStatus(newEntity.getStatus());
			}
		}

	}

}
