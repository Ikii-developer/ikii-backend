package mx.ikii.commons.mapper.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import mx.ikii.commons.mapper.utils.GenericMapper;
import mx.ikii.commons.mapper.utils.StringObjectIdMapper;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductCategorySubcategory;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.commons.persistence.collection.ProductBusiness;

@Mapper(componentModel = "spring", uses = {StringObjectIdMapper.class})
public interface IProductBusinessMapper
    extends GenericMapper<ProductBusiness, ProductBusinessRequest, ProductBusinessResponse> {


  default List<ProductGroupingByBusiness> entityToProductGroupingByBusiness(
      Map<ObjectId, List<ProductBusiness>> productsByBusiness) {
    List<ProductGroupingByBusiness> productByBusiness = new ArrayList<>();

    productsByBusiness.forEach((k, v) -> {
      ProductGroupingByBusiness productGroupingByBusiness = new ProductGroupingByBusiness();
      productGroupingByBusiness.setBusinessId(k.toHexString());
      productGroupingByBusiness.setProducts(entityToResponse(v));
      productByBusiness.add(productGroupingByBusiness);
    });
    return productByBusiness;
  }

  default List<ProductCategorySubcategory> productCategorySubCategory(
      List<ProductBusinessResponse> productBusiness, List<CategoryProduct> categoryProducts) {
    List<CategoryProduct> parentCategories =
        categoryProducts.stream().filter(c -> Objects.nonNull(c.getIsParent()) && c.getIsParent())
            .collect(Collectors.toList());

    // When there are no parent categories, all categories are considered
    boolean hasParents = !parentCategories.isEmpty();
    parentCategories = hasParents ? parentCategories : categoryProducts;

    List<ProductCategorySubcategory> productCategoryResponse = parentCategories.stream().map(c -> {

      // Parent category
      ProductCategorySubcategory.ParentCategory parentCategory =
          ProductCategorySubcategory.ParentCategory.builder()
              .id(c.getId())
              .name(c.getName())
              .description(c.getDescription())
              .build();

      // When there are parent categories the filter accounts all categories of the parent category
      // otherwise only the category is counted
      Predicate<CategoryProduct> categoryPredicate = null;
      if (hasParents) {
        categoryPredicate = sc -> Objects.nonNull(c.getIsParent()) && !sc.getIsParent()
            && sc.getParentProductCategoryId().toHexString().equals(c.getId());
      } else {
        categoryPredicate = sc -> sc.getId().equals(c.getId());
      }

      // SubCategories
      List<ProductCategorySubcategory.SubCategory> subCategories =
          categoryProducts.stream().filter(categoryPredicate).map(subCat -> {
            // SubCategory products
            return ProductCategorySubcategory.SubCategory.builder()
                .id(subCat.getId())
                .name(subCat.getName())
                .description(subCat.getDescription())
                .products(productBusiness.stream()
                    .filter(p -> p.getProductCategoryId().equals(subCat.getId()))
                    .collect(Collectors.toList()))
                .build();
          }).collect(Collectors.toList());

      return ProductCategorySubcategory.builder()
          .parentCategory(parentCategory)
          .subCategory(subCategories)
          .build();
    }).collect(Collectors.toList());

    return productCategoryResponse;
  }

}
