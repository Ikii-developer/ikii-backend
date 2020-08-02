package mx.ikii.customers.repository.impl;

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
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GeoNearOperation;
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

		Point point = new Point(latitude, longitude);
		
		NearQuery nearQuery = NearQuery.near(point, Metrics.KILOMETERS)
				.spherical(true)
				.minDistance(10)
				.maxDistance(100)
				.distanceMultiplier(6371);

		GeoNearOperation geoNearOperation = Aggregation.geoNear(nearQuery, "distance");

		Aggregation agg = Aggregation.newAggregation(geoNearOperation);

		AggregationResults<CustomerAdress> aggResponse = mongoOperations.aggregate(agg, "CustomerAdress",
				CustomerAdress.class);

		return aggResponse.getMappedResults();

	}

	public List<GeoResult<CustomerAdress>> nearByMe2(Double latitude, Double longitude, Double maxDistance) {

		Point location = new Point(latitude, longitude);
		NearQuery nearQuery = NearQuery.near(location).maxDistance(new Distance(maxDistance, Metrics.KILOMETERS));
		GeoResults<CustomerAdress> ca = mongoTemplate.geoNear(nearQuery, CustomerAdress.class, "CustomerAdress");
		List<GeoResult<CustomerAdress>> grCa = ca.getContent();
		
		grCa.forEach(e->{
			System.out.println(e.getContent().getDescription());
		});
		
		return grCa;
	}
	
}
