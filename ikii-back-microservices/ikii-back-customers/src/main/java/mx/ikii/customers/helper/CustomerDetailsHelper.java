package mx.ikii.customers.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;
import mx.ikii.commons.persistence.collection.CustomerDetails;

public class CustomerDetailsHelper {

  /**
   * This method is used to update the instance variables of a existing entity with the instance
   * variables of a new entity
   * 
   * @param entity existing entity
   * @param newEntity new entity
   */
  public static void setUpdateProperties(CustomerDetails entity, CustomerDetails newEntity) {
    if (Objects.nonNull(newEntity)) {
      if (Objects.nonNull(newEntity.getCustomerId())) {
        entity.setCustomerId(newEntity.getCustomerId());
      }
      if (Objects.nonNull(newEntity.getImage())) {
        entity.setImage(newEntity.getImage());
      }
      if (Objects.nonNull(newEntity.getIsValidAccount())) {
        entity.setIsValidAccount(newEntity.getIsValidAccount());
      }
      if (Objects.nonNull(newEntity.getType())) {
        entity.setType(newEntity.getType());
      }
      if (Objects.nonNull(newEntity.getType())) {
        entity.setType(newEntity.getType());
      }
      if (Objects.nonNull(newEntity.getBusinessFavorites())) {
        entity.setBusinessFavorites(newEntity.getBusinessFavorites());
      }
      if (Objects.nonNull(newEntity.getCustomerConektaId())) {
        entity.setCustomerConektaId(newEntity.getCustomerConektaId());
      }
    }
  }

  /**
   * This method toggles the favorite business of the customer, if present removes it and if it is
   * not present it is added
   * 
   * @param favorites
   * @param favorite
   */
  public static void toggleFavorites(List<ObjectId> favorites, ObjectId favorite) {
    if (Objects.isNull(favorites)) {
      favorites = new ArrayList<>();
    }
    if (favorites.contains(favorite)) {
      favorites.remove(favorite);
    } else {
      favorites.add(favorite);
    }
  }

}
