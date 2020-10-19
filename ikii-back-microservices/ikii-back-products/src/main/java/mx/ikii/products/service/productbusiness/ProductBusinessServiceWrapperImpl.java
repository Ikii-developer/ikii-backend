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
import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.mapper.product.IProductBusinessMapper;
import mx.ikii.commons.payload.request.business.BusinessFilterRequest;
import mx.ikii.commons.payload.request.product.ProductBusinessRequest;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.payload.response.product.ProductBusinessResponse;
import mx.ikii.commons.payload.response.product.ProductGroupingByBusiness;
import mx.ikii.commons.persistence.collection.CustomerDetails;
import mx.ikii.commons.persistence.collection.ProductBusiness;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;
import mx.ikii.commons.utils.Nullable;
import mx.ikii.commons.utils.PageHelper;

@Service
@Slf4j
public class ProductBusinessServiceWrapperImpl implements IProductBusinessServiceWrapper {

  @Autowired
  private IProductBusinessMapper productBusinessMapper;

  @Autowired
  private IProductBusinessService productBusinessService;

  @Autowired
  private ICustomerFeignService customerFeignService;

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
    List<ProductGroupingByBusiness> productResponse =
        productBusinessMapper.entityToProductGroupingByBusiness(productsByBusiness);
    setFavorites(productResponse, customerId);
    return productResponse;
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
            e.getMessage());
      }
    }
  }

  private List<ProductBusiness> findProductByKeywordOrBusinessName(List<ProductBusiness> products,
      Pageable pageable, ProductFilter productFilter) {

    products = Objects.isNull(productFilter.getKeywords())
        ? productBusinessService.findAll(pageable).getContent()
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

}
