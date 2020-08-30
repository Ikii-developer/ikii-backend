package mx.ikii.commons.persistence.collection.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import mx.ikii.commons.persistence.collection.Rate;
import mx.ikii.commons.utils.Nullable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessNearByMe {
	private ObjectId id;
	private ObjectId customerId;
	private String name;
	private String image;
	private ObjectId categoryId;
	private String description;
	private String deliveryTime;
	private String closeTieme;
	private Boolean isOpen;
	private Double distance;
	private String status;
	private Double average;
	private boolean favorite;
	@JsonIgnore
	private List<Rate> rates;

	public String getId() {
		return id.toHexString();
	}

	public String getCustomerId() {
		return customerId.toHexString();
	}

	public String getCategoryId() {
		return categoryId.toHexString();
	}

	public Double getAverage() {
		if (Nullable.isNotNull(average)) {
			BigDecimal bd = new BigDecimal(Double.toString(average));
			bd = bd.setScale(1, RoundingMode.HALF_UP);
			return bd.doubleValue();
		}
		return average;
	}

	@JsonProperty("totalRates")
	public Integer getTotalRates() {
		return Nullable.isNullOrEmpty(rates) ? 0 : rates.size();
	}

}
