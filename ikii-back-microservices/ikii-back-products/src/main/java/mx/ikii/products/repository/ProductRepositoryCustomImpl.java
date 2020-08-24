package mx.ikii.products.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Product;

@Repository
public class ProductRepositoryCustomImpl implements IProductRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Product> searchByKeywords(String keyword) {
		
		TextCriteria criteria = TextCriteria.forLanguage("es").matchingAny(keyword);
		
		MatchOperation match = Aggregation.match(criteria);
		
		Aggregation aggregation = Aggregation.newAggregation(match);
		
		List<Product> result = mongoTemplate.aggregate(aggregation, Product.class, Product.class).getMappedResults();
		
		return result;
	}
	
/**
db.getCollection('Product').aggregate([
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
