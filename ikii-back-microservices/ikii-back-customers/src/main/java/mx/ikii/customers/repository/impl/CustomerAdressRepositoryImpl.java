package mx.ikii.customers.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.GeoNearOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.stereotype.Repository;

import mx.ikii.commons.persistence.collection.CustomerAdress;
import mx.ikii.commons.persistence.collection.util.BusinessNearByMe;

@Repository
public class CustomerAdressRepositoryImpl implements ICustomerAdressRepositoryCustom {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, Double maxDistance) {
		GeoJsonPoint p = new GeoJsonPoint(longitude, latitude);
		NearQuery nearQuery = NearQuery.near(p, Metrics.KILOMETERS)
				.spherical(true)
				.maxDistance(new Distance(maxDistance, Metrics.KILOMETERS))
				.distanceMultiplier(6371)
				.inKilometers();
				
		GeoNearOperation geoNear = Aggregation.geoNear(nearQuery, "distance");
		
		LookupOperation lookupBusinessCustomer = LookupOperation.newLookup().from("Business")
				.localField("businessId")
				.foreignField("_id").as("business");
		
		LookupOperation lookupBusinessRate = LookupOperation.newLookup().from("BusinessRate")
				.localField("businessId")
				.foreignField("businessId").as("rate");
		
		ProjectionOperation projectionOperationRenameFields1 = 
				Aggregation.project("businessId", "customerId","location", "description", "street", "distance")
				.and(ArrayOperators.ArrayElemAt.arrayOf("business").elementAt(0)).as("business")
				.and(ArrayOperators.ArrayElemAt.arrayOf("rate").elementAt(0)).as("rate");
		
		ProjectionOperation projectionOperationRenameFields2 = 
				Aggregation.project("businessId", "customerId","location", "distance")
				.andExpression("business.name").as("name")
				.andExpression("business.image").as("image")
				.andExpression("business.categoryId").as("categoryId")
				.andExpression("business.description").as("description")
				.andExpression("street").as("street")
				.andExpression("business.deliveryTime").as("deliveryTime")
				.andExpression("business.closeTime").as("closeTime")
				.andExpression("business.isOpen").as("isOpen")
				.andExpression("business.status").as("status")
				.andExpression("rate.average").as("average")
				.andExpression("rate.rates").as("rates");

		
		TypedAggregation<CustomerAdress> aggregation = 
				TypedAggregation.newAggregation(CustomerAdress.class, geoNear, 
						lookupBusinessCustomer, lookupBusinessRate,
						projectionOperationRenameFields1, projectionOperationRenameFields2);
		
		AggregationResults<BusinessNearByMe> result = 
				mongoTemplate.aggregate(aggregation, BusinessNearByMe.class);
		
		return result.getMappedResults();
	}
	
}
