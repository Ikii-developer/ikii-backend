package mx.ikii.commons.persistence.collection.util;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class BusinessNearByMe {
	
	private ObjectId businessId;
	private ObjectId customerId;
	private String businessName;
	private String businessImage;
	private ObjectId businessCategoryId;
	private String businessDescription;
	private String businessDeliveryTime;
	private String businessCloseTime;
	private Boolean businessIsOpen;
	private Double distance;
	private Double average;

	public String getBusinessId() {
		return businessId.toHexString();
	}

	public String getCustomerId() {
		return customerId.toHexString();
	}

	public String getBusinessCategoryId() {
		return businessCategoryId.toHexString();
	}

}
