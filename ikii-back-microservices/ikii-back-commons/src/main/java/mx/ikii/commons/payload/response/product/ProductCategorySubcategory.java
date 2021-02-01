package mx.ikii.commons.payload.response.product;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductCategorySubcategory {
  
  private ParentCategory parentCategory;
  private List<SubCategory> subCategory;

  @Data
  @Builder
  public static class ParentCategory {

    private String id;
    private String name;
    private String description;
  }

  @Data
  @Builder
  public static class SubCategory {
    private String id;
    private String name;
    private String description;
    private List<ProductBusinessResponse> products;

  }

}
