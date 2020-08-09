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

	@Autowired
	private MongoOperations mongoOperations;

	public List<BusinessNearByMe> nearByMe(Double latitude, Double longitude, Double maxDistance) {
		List<BusinessNearByMe> result = null;
		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		
		GeoJsonPoint p = new GeoJsonPoint(longitude, latitude);
		NearQuery nearQuery = NearQuery.near(p, Metrics.KILOMETERS)
				//.minDistance(new Distance(1, Metrics.KILOMETERS))
				.spherical(true)
				.maxDistance(new Distance(maxDistance, Metrics.KILOMETERS))
				.distanceMultiplier(6371)
				.inKilometers();
		
		list.add(Aggregation.geoNear(nearQuery, "distance"));
		
		LookupOperation lookup = LookupOperation.newLookup().from("Business").localField("businessId")
				.foreignField("_id").as("business");
		list.add(lookup);
		
		ProjectionOperation projectionOperationRenameFields = 
				Aggregation.project("businessId", "customerId")
				.andExclude("postalCode", "street", "colony","city","interiorNumber","description","nickname",
						"location","distance","isMain", "isCurrent","isValidate","business._id","business.customerId")
				.andExpression("business.name").as("businessName")
				.andExpression("business.image").as("businessImage")
				.andExpression("business.categoryId").as("businessCategoryId")
				.andExpression("business.description").as("businessDescription")
				.andExpression("business.deliveryTime").as("businessDeliveryTime")
				.andExpression("business.closeTime").as("businessCloseTime")
				.andExpression("business.isOpen").as("businessIsOpen");
		list.add(projectionOperationRenameFields);
		
		TypedAggregation<BusinessNearByMe> agg = Aggregation.newAggregation(BusinessNearByMe.class, list);
		result = mongoTemplate.aggregate(agg, BusinessNearByMe.class).getMappedResults();
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
