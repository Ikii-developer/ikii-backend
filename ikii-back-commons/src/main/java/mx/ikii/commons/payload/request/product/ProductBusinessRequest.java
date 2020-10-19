package mx.ikii.commons.payload.request.product;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Data;

@Data
public class ProductBusinessRequest {

  private String productModelId;
  private String businessId;
  private String productCategoryId;
  private String measureUnit;
  private String code;
  private String name;
  private String description;
  private Map<String, String> fullDescription;
  private BigDecimal price;
  private String pathImage;

}
