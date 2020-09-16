package mx.ikii.products.repository.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.ProductModel;

@Repository
public class ProductRepositoryCustomImpl implements IProductRepositoryCustom {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<ProductModel> searchByKeywords(String keyword) {
		
		TextCriteria criteria = TextCriteria.forLanguage("es").matchingAny(keyword);
		
		MatchOperation match = Aggregation.match(criteria);
		
		Aggregation aggregation = Aggregation.newAggregation(match);
		
		List<ProductModel> result = mongoTemplate.aggregate(aggregation, ProductModel.class, ProductModel.class).getMappedResults();
		
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
