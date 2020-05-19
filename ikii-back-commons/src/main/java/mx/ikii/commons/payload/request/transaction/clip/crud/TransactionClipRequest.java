package mx.ikii.commons.payload.request.transaction.clip.crud;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Required;

import lombok.Data;

/**
 * This class is used as the incoming DTO with all the information related to
 * the clip transaction
 * 
 * @author Arturo Velázquez Vargas
 *
 */
public class TransactionClipRequest {

	private String id;

	@NotNull
	private String description;

	@NotNull
	private BigDecimal amount;

	@NotNull
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
