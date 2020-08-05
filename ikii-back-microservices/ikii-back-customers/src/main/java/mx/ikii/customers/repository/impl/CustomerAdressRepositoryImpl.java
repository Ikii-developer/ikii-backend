package mx.ikii.customers.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.CustomerAdress;

@Repository
public class CustomerAdressRepositoryImpl implements ICustomerAdressRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoOperations mongoOperations;

	public List<CustomerAdress> nearByMe(Double latitude, Double longitude, Double maxDistance) {
		List<CustomerAdress> result = null;
		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		
		Point p = new Point(longitude, latitude);
		NearQuery nearQuery = NearQuery.near(p, Metrics.KILOMETERS).maxDistance(maxDistance);
		
		list.add(Aggregation.geoNear(nearQuery, "distance"));
		list.add(Aggregation.project("id", "location"));
		
		TypedAggregation<CustomerAdress> agg = Aggregation.newAggregation(CustomerAdress.class, list);
		result = mongoOperations.aggregate(agg, CustomerAdress.class).getMappedResults();
		return result;

	}

	public List<GeoResult<CustomerAdress>> nearByMe2(Double latitude, Double longitude, Double maxDistance) {
		Point location = new Point(latitude, longitude);
		NearQuery nearQuery = NearQuery.near(location).maxDistance(new Distance(maxDistance, Metrics.KILOMETERS));
		GeoResults<CustomerAdress> ca = mongoTemplate.geoNear(nearQuery, CustomerAdress.class, "CustomerAdress");
		List<GeoResult<CustomerAdress>> grCa = ca.getContent();

		grCa.forEach(e -> {
			System.out.println(e.getContent().getDescription());
		});

		return grCa;
	}

}
