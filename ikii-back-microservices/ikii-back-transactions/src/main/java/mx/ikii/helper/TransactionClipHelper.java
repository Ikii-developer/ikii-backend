package mx.ikii.helper;

import mx.ikii.commons.persistence.collection.TransactionIkii;
import mx.ikii.commons.utils.Nullable;

/**
 * This class helps the transactions business logic decoupling the code
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
public class TransactionClipHelper {
	/**
	 * This method is used to update the instance variables of a existing entity
	 * with the instance variables of a new entity
	 * 
	 * @param entity    existing entity
	 * @param newEntity new entity
	 */

	public static void setUpdateProperties(TransactionIkii entity, TransactionIkii newEntity) {

		if (Nullable.isNotNull(newEntity)) {

			if (Nullable.isNotNull(newEntity.getDescription())) {

				entity.setDescription(newEntity.getDescription());
			}
			if (Nullable.isNotNull(newEntity.getAmount())) {

				entity.setAmount(newEntity.getAmount());
			}
			if (Nullable.isNotNull(newEntity.getDate())) {

				entity.setDate(newEntity.getDate());
			}
		}
	}

}
