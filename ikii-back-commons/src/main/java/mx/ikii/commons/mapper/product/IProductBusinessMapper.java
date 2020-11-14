package mx.ikii.commons.mapper.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
import mx.ikii.commons.utils.Nullable;

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

  default ProductCategorySubcategory productCategorySubCategory(
      List<ProductBusinessResponse> productBusiness, List<CategoryProduct> categoryProducts) {

    ProductCategorySubcategory productCategorySubCategory = new ProductCategorySubcategory();
    List<ProductBusinessResponse> productBusinessResponse =
        new ArrayList<ProductBusinessResponse>();

    List<CategoryProduct> parentCategories = categoryProducts.stream()
        .filter(c -> Objects.nonNull(c.getIsParent()) && c.getIsParent())
        .collect(Collectors.toList());

    parentCategories.forEach(c -> {

      // Category
      ProductCategorySubcategory.ParentCategory parentCategory =
          new ProductCategorySubcategory.ParentCategory();
      parentCategory.setId(c.getId());
      parentCategory.setName(c.getName());
      parentCategory.setDescription(c.getDescription());
      productCategorySubCategory.setParentCategory(parentCategory);

      // SubCategories
      List<ProductCategorySubcategory.SubCategory> subCategories =
          categoryProducts.stream()
              .filter(sc -> Objects.nonNull(c.getIsParent()) && !sc.getIsParent()
                  && sc.getParentProductCategoryId().toHexString().equals(c.getId()))
              .map(subCat -> {

                ProductCategorySubcategory.SubCategory subCategory =
                    new ProductCategorySubcategory.SubCategory();
                subCategory.setId(subCat.getId());
                subCategory.setName(subCat.getName());
                subCategory.setDescription(subCat.getDescription());

                // Products
                productBusinessResponse.addAll(productBusiness.stream()
                    .filter(p -> p.getProductCategoryId().equals(subCat.getId()))
                    .collect(Collectors.toList()));

                return subCategory;
              }).collect(Collectors.toList());
      productCategorySubCategory.setSubCategory(subCategories);
    });
    productCategorySubCategory.setProductBusinessResponse(productBusinessResponse);

    return productCategorySubCategory;
  }

}
