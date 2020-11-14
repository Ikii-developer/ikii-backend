package mx.ikii.products.repository.productbusiness;

import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;
import mx.ikii.commons.payload.request.product.ProductFilter;
import mx.ikii.commons.persistence.collection.ProductBusiness;
import mx.ikii.commons.utils.Nullable;

@Repository
public class ProductBusinessRepositoryCustomImpl implements IProductBusinessRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<ProductBusiness> searchByKeywords(ProductFilter productFilter) {
    List<AggregationOperation> aggregations = new ArrayList<>();

    TextCriteria criteria = TextCriteria.forLanguage("es").matchingAny(productFilter.getKeywords());
    MatchOperation match = Aggregation.match(criteria);
    aggregations.add(match);

    MatchOperation matchBusiness = null;
    if (!Nullable.isNullOrEmpty(productFilter.getProductCategoryId())) {
      matchBusiness = Aggregation.match(Criteria.where("productCategoryId")
          .is(new ObjectId(productFilter.getProductCategoryId())));
      aggregations.add(matchBusiness);
    }

    Aggregation aggregation = Aggregation.newAggregation(aggregations);

    List<ProductBusiness> result = mongoTemplate
        .aggregate(aggregation, ProductBusiness.class, ProductBusiness.class).getMappedResults();

    return result;
  }

  /**
   * db.getCollection('ProductModel').aggregate([ { $match: { $text: { $search: "cafe", $language:
   * "es", $caseSensitive: true, $diacriticSensitive:true } } } ])
   */

}
