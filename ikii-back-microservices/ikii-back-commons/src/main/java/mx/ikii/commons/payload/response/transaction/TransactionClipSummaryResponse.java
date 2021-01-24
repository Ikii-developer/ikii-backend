package mx.ikii.commons.payload.response.transaction;

/**
 * This class is used as the incoming DTO with all the information related to
 * sum of all the transactions for a specific user
 * 
 * @author Arturo Velázquez Vargas
 *
 */
public class TransactionClipSummaryResponse {

	/**
	 * Id of the underlying user
	 */
	private String userId;

	/**
	 * The sum of all the transactions
	 */
	private Double sum;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

}
