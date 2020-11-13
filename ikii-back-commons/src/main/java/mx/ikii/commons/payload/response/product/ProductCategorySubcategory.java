package mx.ikii.commons.payload.response.product;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class ProductCategorySubcategory {
  
  private ParentCategory parentCategory;
  private List<SubCategory> subCategory;
  private List<ProductBusinessResponse> productBusinessResponse = new ArrayList<ProductBusinessResponse>();

  @Data
  public static class ParentCategory {

    private String id;
    private String name;
    private String description;
  }

  @Data
  public static class SubCategory {
    private String id;
    private String name;
    private String description;
  }

}
