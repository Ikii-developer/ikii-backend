package mx.ikii.commons.payload.response.transaction;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**
 * This class is used as the outcoming DTO with all the information related to
 * the clip transaction
 * 
 * @author Arturo Velázquez Vargas
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionClipResponse {

	private String id;

	private String description;

	private BigDecimal amount;

	private String userId;

	private Date date;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
