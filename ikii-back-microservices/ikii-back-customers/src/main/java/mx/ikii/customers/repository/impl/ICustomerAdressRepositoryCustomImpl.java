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
public class ICustomerAdressRepositoryCustomImpl implements ICustomerAdressRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private MongoOperations mongoOperations;

	public List<CustomerAdress> nearByMe2(String latitude, String longitude, Double distance) {

		Point location = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
		NearQuery nearQuery = NearQuery.near(location).maxDistance(new Distance(distance, Metrics.KILOMETERS));
		GeoResults<CustomerAdress> ca = mongoOperations.geoNear(nearQuery, CustomerAdress.class, "CustomerAdress");
		List<GeoResult<CustomerAdress>> grCa = ca.getContent();

		return null;
	}
	
	public List<CustomerAdress> nearByMe3(String latitude, String longitude, Double distance) {
		
		Point point = new Point(Double.parseDouble(latitude), Double.parseDouble(longitude));
		NearQuery nearQuery = NearQuery.near(point).spherical(true).maxDistance(distance).distanceMultiplier(6371)
				.inKilometers();

		GeoNearOperation geoNearOperation = Aggregation.geoNear(nearQuery, "distance");

		Aggregation agg = Aggregation.newAggregation(geoNearOperation);

		AggregationResults<CustomerAdress> aggResponse = mongoTemplate.aggregate(agg, "CustomerAdress",
				CustomerAdress.class);

		aggResponse.getMappedResults();

		return null;
	}

}
