package mx.ikii.products.service.productbusiness;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.feignclient.service.impl.IBusinessFeignService;
import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.mapper.business.IBusinessMapper;
import mx.ikii.commons.mapper.product.IProductBusinessMapper;
import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.business.BusinessResponse;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductCategorySubcategory;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;
import mx.ikii.commons.persistence.collection.Business;
import mx.ikii.commons.persistence.collection.CategoryProduct;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.ProductBusiness;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.products.service.categoryproduct.ICategoryProductService;

@Service
@Slf4j
public class ProductBusinessServiceWrapperImpl implements IProductBusinessServiceWrapper {

  @Autowired
  private IProductBusinessMapper productBusinessMapper;

  @Autowired
  private IBusinessMapper businessMapper;

  @Autowired
  private IProductBusinessService productBusinessService;

  @Autowired
  private ICustomerFeignService customerFeignService;

  @Autowired
  private IBusinessFeignService businessFeignService;

  @Autowired
  private ICategoryProductService categoryProductService;

  @Override
  public ProductBusinessResponse findById(String id) {
    return productBusinessMapper.entityToResponse(productBusinessService.findById(id));
  }

  @Override
  public ProductBusinessResponse getByProductName(String productName) {
    return productBusinessMapper.entityToResponse(productBusinessService.findByName(productName));
  }

  @Override
  public Page<ProductBusinessResponse> findAll(Pageable pageable) {
    Page<ProductBusiness> product = productBusinessService.findAll(pageable);
    List<ProductBusinessResponse> productResponse =
        productBusinessMapper.entityToResponse(product.getContent());
    return PageHelper.createPage(productResponse, pageable, product.getTotalElements());
  }

  @Override
  public ProductBusinessResponse create(ProductBusinessRequest productRequest) {
    log.info("creating business product {}", productRequest);
    ProductBusiness product = productBusinessMapper.requestToEntity(productRequest);
    return productBusinessMapper.entityToResponse(productBusinessService.create(product));
  }

  @Override
  public List<ProductBusinessResponse> createBulk(List<ProductBusinessRequest> productRequest) {
    return productRequest.stream().map(productRequestMap -> {
      return this.create(productRequestMap);
    }).collect(Collectors.toList());
  }

  @Override
  public ProductBusinessResponse update(ProductBusinessRequest productRequest, String id) {
    ProductBusiness product = productBusinessMapper.requestToEntity(productRequest);
    return productBusinessMapper.entityToResponse(productBusinessService.update(product, id));
  }

  @Override
  public void delete(String id) {
    productBusinessService.delete(id);
  }

  @Override
  public List<ProductGroupingByBusiness> filterProduct(Pageable pageable, String customerId,
      ProductFilter productFilter) {
    List<ProductBusiness> products = null;

    products = Objects.isNull(productFilter.getBusinessId())
        ? findProductByKeywordOrBusinessName(products, pageable, productFilter)
        : productBusinessService.findAllByBussinessId(pageable,
            new ObjectId(productFilter.getBusinessId()));
    Map<ObjectId, List<ProductBusiness>> productsByBusiness =
        products.stream().collect(Collectors.groupingBy(p -> p.getBusinessId()));

    List<Business> allBusiness = null;
    try {
      allBusiness = businessFeignService.getAll();
    } catch (Exception e) {
      log.warn("Not possible to getAll business", e);
    }
    Map<BusinessResponse, List<ProductBusiness>> productsByBusinessResponse =
        setBusiness(productsByBusiness, allBusiness);
    List<ProductGroupingByBusiness> productResponse =
        productBusinessMapper.entityToProductGroupingByBusiness(productsByBusinessResponse);

    setFavorites(productResponse, customerId);
    return productResponse;
  }

  private Map<BusinessResponse, List<ProductBusiness>> setBusiness(
      Map<ObjectId, List<ProductBusiness>> productsByBusiness, List<Business> allBusiness) {
    return productsByBusiness.entrySet().stream()
        .collect(Collectors.toMap(
            k -> businessMapper.entityToResponse(allBusiness.stream()
                .filter(business -> business.getId().equals(k.getKey().toHexString()))
                .findFirst().orElseGet(() -> new Business())),
            v -> v.getValue()));
  }


  private void setFavorites(List<ProductGroupingByBusiness> businessProducts, String customerId) {
    if (!Objects.isNull(customerId)) {
      try {
        CustomerDetails customerDetails =
            customerFeignService.getCustomerDetailsByCustomerId(customerId);
        List<ObjectId> productsFavorites = customerDetails.getProductFavorites();

        if (!Nullable.isNullOrEmpty(productsFavorites)) {
          businessProducts = businessProducts.stream().map(productMap -> {

            productMap.getProducts().stream().forEach(prod -> {
              if (productsFavorites.contains(new ObjectId(prod.getId()))) {
                prod.setFavorite(true);
              }
            });
            return productMap;
          }).collect(Collectors.toList());
        }
      } catch (Exception e) {
        log.warn("Not possible to get CustomerDetails for customerId {}, {}", customerId,
            e);
      }
    }
  }

  private List<ProductBusiness> findProductByKeywordOrBusinessName(List<ProductBusiness> products,
      Pageable pageable, ProductFilter productFilter) {

    products = Objects.isNull(productFilter.getKeywords())
        ? productBusinessService.findAll(Pageable.unpaged()).getContent()
        : productBusinessService.filterProduct(pageable, productFilter);

    if (Nullable.isNullOrEmpty(products)) {
      BusinessFilterRequest businesFilterReq = new BusinessFilterRequest();
      businesFilterReq.setKeywords(productFilter.getKeywords());

      List<BusinessNearByMe> business =
          customerFeignService.nearByMe(productFilter.getCoordinates().getLatitude(),
              productFilter.getCoordinates().getLongitude(), new Double(5), null, businesFilterReq);

      products = productBusinessService.findByBusinessIdIn(
          business.stream().map(b -> b.getBusinessIdObjectId()).collect(Collectors.toList()));
    }

    return products;
  }

  @Override
  public List<ProductCategorySubcategory> findProductByCategory(String businessId) {

    // Retrieve products for business
    List<ProductBusiness> productBusiness =
        productBusinessService.findAllByBussinessId(Pageable.unpaged(), new ObjectId(businessId));

    // Retrieve product-category for business
    List<CategoryProduct> categoryProducts = categoryProductService.findByBusinessId(businessId);

    return productBusinessMapper.productCategorySubCategory(
        productBusinessMapper.entityToResponse(productBusiness), categoryProducts);

  }

}
