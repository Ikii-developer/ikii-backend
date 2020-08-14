package mx.ikii.customers.helper;

import java.util.List;

import org.bson.types.ObjectId;

import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.utils.Nullable;

public class CustomerDetailsHelper {

	/**
	 * This method is used to update the instance variables of a existing entity
	 * with the instance variables of a new entity
	 * 
	 * @param entity    existing entity
	 * @param newEntity new entity
	 */
	public static void setUpdateProperties(CustomerDetails entity, CustomerDetails newEntity) {
		if (Nullable.isNotNull(newEntity)) {
			if (Nullable.isNotNull(newEntity.getCustomerId())) {
				entity.setCustomerId(newEntity.getCustomerId());
			}
			if (Nullable.isNotNull(newEntity.getImage())) {
				entity.setImage(newEntity.getImage());
			}
			if (Nullable.isNotNull(newEntity.getIsValidAccount())) {
				entity.setIsValidAccount(newEntity.getIsValidAccount());
			}
			if (Nullable.isNotNull(newEntity.getType())) {
				entity.setType(newEntity.getType());
			}
			if (Nullable.isNotNull(newEntity.getType())) {
				entity.setType(newEntity.getType());
			}
			if (Nullable.isNotNull(newEntity.getBusinessFavorites())) {
				entity.setBusinessFavorites(newEntity.getBusinessFavorites());
			}
		}
	}

	/**
	 * This method toggles the favorite business of the customer, if present removes
	 * it and if it is not present it is added
	 * 
	 * @param favorites
	 * @param favorite
	 */
	public static void toggleFavorites(List<ObjectId> favorites, ObjectId favorite) {
		if (favorites.contains(favorite)) {
			favorites.remove(favorite);
		} else {
			favorites.add(favorite);
		}
	}

}
