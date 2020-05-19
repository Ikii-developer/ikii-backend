package mx.ikii.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.ikii.commons.payload.request.transaction.clip.crud.TransactionClipRequest;
import mx.ikii.commons.payload.response.transaction.TransactionClipReport;
import mx.ikii.commons.payload.response.transaction.TransactionClipResponse;
import mx.ikii.commons.payload.response.transaction.TransactionClipSummaryResponse;

/**
 * 
 * This controller interface contains the methods related to the payclip
 * assessment requirements of transactions and the CRUD methods
 * 
 * @author Arturo Isaac Velazquez Vargas
 * 
 * @see <a href=
 *      "https://github.com/cesaralcancio/simple-test/blob/master/README.md">payclip_assessment</a>
 *
 */
@RestController
@RequestMapping("/")
public interface ITransactionClipController {

	/**
	 * Payclip assesment requitement-1:
	 * 
	 * This feature adds a transaction to the user specified in <user_id> using the
	 * information specified in <transaction_data>.
	 * 
	 * @param transactionRequest request with the needed information of the
	 *                           transaction
	 * @param userId             id of the user to be added
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@PostMapping("/users/{userId}")
	ResponseEntity<TransactionClipResponse> add(TransactionClipRequest transactionRequest, String userId);

	/**
	 * Payclip assesment requitement-2:
	 * 
	 * This feature returns the transaction specified in the transaction_id.
	 * 
	 * If the user_id is not the user_id that corresponds with the user_id for the
	 * specified transaction.
	 * 
	 * @param userId id of the user to owner of the transaction
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@GetMapping("{id}/users/{userId}")
	ResponseEntity<TransactionClipResponse> getByIdByUserId(String id, String userId);

	/**
	 * Payclip assesment requitement-3:
	 * 
	 * This feature prints all the transactions associated with the user specified
	 * by user_id. The transactions should be in chronological order.
	 * 
	 * If the user_id does not exist, then the response should return an empty list
	 * 
	 * @param userId id of the user owner of all the transactions
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@GetMapping("/users/{userId}")
	ResponseEntity<List<TransactionClipResponse>> getAllByUserId(String userId);

	/**
	 * Payclip assessment requirement4
	 * 
	 * This command sums all the transactions associated with the user specified by
	 * user_id.
	 * 
	 * @param userId id of the user to generate the sum of the transactions
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@GetMapping("/users/{userId}/sum")
	ResponseEntity<TransactionClipSummaryResponse> sumByUserId(String userId);

	/**
	 * Payclip assessment requirement-5
	 * 
	 * This command returns all transactions accumulated by week, where the week
	 * starts on Friday and finishes on Thursday. Also, if it is the first day of
	 * the month, it should start the next week
	 * 
	 * @param userId id of the user to generate the week transaction report
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@GetMapping("/users/{userId}/report")
	ResponseEntity<List<TransactionClipReport>> getReportByUserId(String userId);

	/**
	 * Payclip assessment requirement-6 This command returns one transaction in a
	 * randmon way
	 * 
	 * @return
	 */
	@GetMapping("/random")
	ResponseEntity<TransactionClipResponse> getRandom();

	/**
	 * This method is used to get the transaction based on the id
	 * 
	 * @param id transactionId
	 * @return the HTTP DTO response wrapping the transaction
	 */
	@GetMapping("{id}")
	ResponseEntity<TransactionClipResponse> getById(String id);

	/**
	 * This method is used to get all the transactions
	 * 
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@GetMapping
	ResponseEntity<Page<TransactionClipResponse>> getAll(Pageable pageable);

	/**
	 * This method is used to create a transaction
	 * 
	 * @param transactionRequest used to create the transaction resource
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@PostMapping
	ResponseEntity<TransactionClipResponse> create(TransactionClipRequest transactionRequest);

	/**
	 * This method is used to update a transaction
	 * 
	 * @param transactionRequest used to update the transaction resource
	 * @return the HTTP DTO response wrapping the transactions
	 */
	@PutMapping("{id}")
	ResponseEntity<TransactionClipResponse> update(TransactionClipRequest transactionRequest, String id);

	/**
	 * This method is used to delete the transaction based on the id
	 */
	@DeleteMapping("{id}")
	ResponseEntity<Void> delete(String id);
}
