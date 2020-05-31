package mx.ikii.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.payload.request.transaction.clip.crud.TransactionIkiiRequest;
import mx.ikii.commons.payload.response.transaction.TransactionClipReport;
import mx.ikii.commons.payload.response.transaction.TransactionIkiiResponse;
import mx.ikii.commons.payload.response.transaction.TransactionClipSummaryResponse;

/**
 * 
 * This class includes the logic to each one of the assessment points and acts
 * as a wrapper layer to the  business logic in the TransactioClip service
 * 
 * @author Arturo Isaac Velázquez Vargas
 * @see <a href=
 *      "https://github.com/cesaralcancio/simple-test/blob/master/README.md">payclip_assessment</a>
 * 
 */
public interface ITransactionClipServiceWrapper {

	/**
	 * Requirement-1 This method is used to add a transaction and attach it to the
	 * user
	 * 
	 * @param transaction incoming id of the transaction to be fetched
	 * @param userId      incoming id of the user
	 * @return
	 */
	TransactionIkiiResponse add(TransactionIkiiRequest transaction, String userId);

	/**
	 * Requirement-2 This method is used to find the transaction based on the userId
	 * 
	 * @param transactionId incoming id of the transaction to be fetched
	 * @param userId        incoming id of the user
	 * @return the DTO response representing the transaction fetched
	 */
	TransactionIkiiResponse findByTransactionIdByUserId(String transactionId, String userId);

	/**
	 * Requirement-3 This method is used to list all the transactions attached to a
	 * particular user
	 * 
	 * @param userId incoming id of the user
	 * @return the DTO response representing the transactions fetched
	 */
	List<TransactionIkiiResponse> findAllByUserId(String userId);

	/**
	 * Requirement-4 This method is used to sum all the transactions attached to a
	 * particular user
	 * 
	 * @param userId incoming id of the user
	 * @return the DTO response representing the sum of each one on the transactions
	 *         of the user
	 */
	TransactionClipSummaryResponse sumByUserId(String userId);

	/**
	 * Requirement-5 This method is used to generate a list all transactions
	 * accumulated by week, where the week starts on Friday and finishes on Thursday
	 * 
	 * @param userId incoming id of the user
	 * @return the DTO response representing the transactions report of the user
	 */
	List<TransactionClipReport> getReportByUserId(String userId);

	/**
	 * Requirement-6 This method is used to retrieve a random transaction from a
	 * random user
	 * 
	 * @return the DTO response representing the random transaction
	 */
	TransactionIkiiResponse findRandom();

	/**
	 * 
	 * @param id incoming id of the transaction to be fetched
	 * @return
	 */
	TransactionIkiiResponse findById(String id);

	/**
	 * 
	 * @param pageable represents the current page and the size of the pagination
	 * @return the DTO responses representing the transaction fetched based on the
	 *         pagination
	 */
	Page<TransactionIkiiResponse> findAll(Pageable pageable);

	/**
	 * 
	 * @param transaction transaction DTO to be created
	 * @return the DTO response representing the transaction inserted
	 */
	TransactionIkiiResponse create(TransactionIkiiRequest transaction);

	/**
	 * 
	 * @param transaction transaction DTO to be updated
	 * @param id          incoming id of the transaction to be updated
	 * @return the DTO response representing the transaction updated
	 */
	TransactionIkiiResponse update(TransactionIkiiRequest transaction, String id);

	/**
	 * 
	 * @param id incoming id of the transaction to be deleted
	 */
	void delete(String id);

}
