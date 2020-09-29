package mx.ikii.business.repository.custom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.Business;

@Repository
public class BusinessRepositoryCustomImpl implements IBusinessRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Business> filterByBusinessName(String keyword) {
		
		TextCriteria criteria = TextCriteria.forLanguage("es").matchingAny(keyword);
		
		MatchOperation match = Aggregation.match(criteria);
		
		Aggregation aggregation = Aggregation.newAggregation(match);
		
		return mongoTemplate.aggregate(aggregation, Business.class, Business.class).getMappedResults();
	}
	
/**
db.getCollection('ProductModel').aggregate([
    { $match: { 
            $text: { 
                $search: "pollo",
                $language: "es",
                $caseSensitive: true,
                $diacriticSensitive:true
            }
        } 
    }
])	
 */
	
}
