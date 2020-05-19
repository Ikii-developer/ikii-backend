package mx.ikii.commons.payload.response.transaction;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class is used to represent the user transaction report
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TransactionClipReport {

	private String userId;

	/**
	 * This variable represents the start date e.g 2019-11-22 on Friday
	 */
	private Date weekStartDate;

	/**
	 * This variable represents the end date e.g 2019-11-28 on Thursday
	 */
	private Date weekEndDate;

	/**
	 * This variable represents the quantity of transactions inside the week
	 */
	private Integer quantity;

	/**
	 * This variable represents the sum amount of the period/week
	 */
	private Double amount;

	/**
	 * This variable represents the total amount before the week start date
	 */
	private Double totalAmount;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getWeekStartDate() {
		return weekStartDate;
	}

	public void setWeekStartDate(Date weekStartDate) {
		this.weekStartDate = weekStartDate;
	}

	public Date getWeekEndDate() {
		return weekEndDate;
	}

	public void setWeekEndDate(Date weekEndDate) {
		this.weekEndDate = weekEndDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
