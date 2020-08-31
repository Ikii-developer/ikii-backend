package mx.ikii.products.repository.productbusiness;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.ProductBusiness;

@Repository
public class ProductBusinessRepositoryCustomImpl implements IProductBusinessRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<ProductBusiness> searchByKeywords(String keyword) {
		
		TextCriteria criteria = TextCriteria.forLanguage("es").matchingAny(keyword);
		
		MatchOperation match = Aggregation.match(criteria);
		
		Aggregation aggregation = Aggregation.newAggregation(match);
		
		List<ProductBusiness> result = mongoTemplate.aggregate(aggregation, ProductBusiness.class, ProductBusiness.class).getMappedResults();
		
		return result;
	}
	
/**
db.getCollection('ProductModel').aggregate([
    { $match: { 
            $text: { 
                $search: "cafe",
                $language: "es",
                $caseSensitive: true,
                $diacriticSensitive:true
            }
        } 
    }
])	
 */

}
