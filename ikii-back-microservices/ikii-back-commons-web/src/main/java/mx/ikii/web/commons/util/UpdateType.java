package mx.ikii.web.commons.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UpdateType {

  PRODUCTS("ProductBusiness"), CUSTOMER("Customer"), BUSINESS("Business");

  private final String name;

}
