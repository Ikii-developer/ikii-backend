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

	@Autowired
	private MongoOperations mongoOperations;

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
		
		ProjectionOperation projectionOperationRenameFields1 = 
				Aggregation.project("businessId", "customerId","location", "description")
				.and(ArrayOperators.ArrayElemAt.arrayOf("business").elementAt(0)).as("business");
		
		ProjectionOperation projectionOperationRenameFields2 = 
				Aggregation.project("businessId", "customerId","location")
				.andExpression("business.name").as("businessName")
				.andExpression("business.image").as("businessImage")
				.andExpression("business.categoryId").as("businessCategoryId")
//				.andExpression("business.description").as("businessDescription")
				.andExpression("description").as("businessDescription")
				.andExpression("business.deliveryTime").as("businessDeliveryTime")
				.andExpression("business.closeTime").as("businessCloseTime")
				.andExpression("business.isOpen").as("businessIsOpen");
		
		TypedAggregation<CustomerAdress> aggregation = 
				TypedAggregation.newAggregation(CustomerAdress.class, geoNear, 
						lookupBusinessCustomer, 
						projectionOperationRenameFields1, projectionOperationRenameFields2);
		
		AggregationResults<BusinessNearByMe> result = 
				mongoTemplate.aggregate(aggregation, BusinessNearByMe.class);
		
		return result.getMappedResults();
		
	}
	
	/**
	 * QUERY MONGO
		db.getCollection('CustomerAddress').aggregate([
		    {
		        $geoNear: {
		            spherical:true,
		            near: {type: 'Point', coordinates: [-99.129110, 19.411911]},
		            distanceField: 'distance',
		            maxDistance: 1000,
		            distanceMultiplier: 6371
		        }    
		    },
		    {
		        $lookup: {
		            from: 'Business', 
		            foreignField: '_id', 
		            localField: 'businessId', 
		            as: 'business'
		        }
		    },
		    {
		        $project: {
		            "businessId":1,"customerId":1,"nickname":1,
		            "business": { $arrayElemAt: [ "$business", 0 ] }
		        }
		    },
		    {
		        $project: {
		            "businessId":1, "customerId":1, "nickname":1,
		            "business.name":1,"business.image":1,"business.categoryId":1,
		            "business.description":1,"business.deliveryTime":1,
		            "business.closeTime":1,"business.isOpen":1
		        }
		    }
		])
	 */

	private List<GeoResult<CustomerAdress>> nearByMe2(Double latitude, Double longitude, Double maxDistance) {
		Point location = new Point(latitude, longitude);
		NearQuery nearQuery = NearQuery.near(location).maxDistance(new Distance(maxDistance, Metrics.KILOMETERS));
		GeoResults<CustomerAdress> ca = mongoTemplate.geoNear(nearQuery, CustomerAdress.class, "CustomerAdress");
		List<GeoResult<CustomerAdress>> grCa = ca.getContent();

		grCa.forEach(e -> {
			System.out.println(e.getContent().getDescription());
		});
		return grCa;
	}
	
	private List<CustomerAdress> nearByMe3(Double latitude, Double longitude, Double maxDistance) {
		List<CustomerAdress> result = null;
		List<AggregationOperation> list = new ArrayList<AggregationOperation>();
		
		GeoJsonPoint p = new GeoJsonPoint(longitude, latitude);
		NearQuery nearQuery = NearQuery.near(p, Metrics.KILOMETERS)
				//.minDistance(new Distance(1, Metrics.KILOMETERS))
				.spherical(true)
				.maxDistance(new Distance(maxDistance, Metrics.KILOMETERS))
				.distanceMultiplier(6371)
				.inKilometers();
		
		list.add(Aggregation.geoNear(nearQuery, "distance"));
		
		TypedAggregation<CustomerAdress> agg = Aggregation.newAggregation(CustomerAdress.class, list);
		result = mongoTemplate.aggregate(agg, CustomerAdress.class).getMappedResults();

		result.forEach(e->System.out.println(e.getDescription()));
		
		return result;
	}

}
