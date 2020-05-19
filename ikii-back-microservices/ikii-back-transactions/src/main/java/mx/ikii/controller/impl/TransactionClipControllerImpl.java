package mx.ikii.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.transaction.clip.crud.TransactionClipRequest;
import mx.ikii.commons.payload.response.transaction.TransactionClipReport;
import mx.ikii.commons.payload.response.transaction.TransactionClipResponse;
import mx.ikii.commons.payload.response.transaction.TransactionClipSummaryResponse;
import mx.ikii.controller.ITransactionClipController;
import mx.ikii.service.ITransactionClipServiceWrapper;

/**
 * @author Arturo Isaac Velazquez Vargas
 * 
 *         This controller interface contains the CRUD methods related to the
 *         transactions and the methods related to the payclip assessment
 *         requirements
 *
 */
@Component
public class TransactionClipControllerImpl implements ITransactionClipController {

	@Autowired
	private ITransactionClipServiceWrapper transactionClipServiceWrapper;

	/**
	 * ----------------Payclip assessment requirement-1----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> add(@RequestBody TransactionClipRequest transactionRequest,
			@PathVariable String userId) {
		return ResponseEntity.ok(transactionClipServiceWrapper.add(transactionRequest, userId));
	}

	/**
	 * ----------------Payclip assessment requirement-2----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> getByIdByUserId(@PathVariable String id,
			@PathVariable String userId) {
		return ResponseEntity.ok(transactionClipServiceWrapper.findByTransactionIdByUserId(id, userId));
	}

	/**
	 * ----------------Payclip assessment requirement-3----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TransactionClipResponse>> getAllByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(transactionClipServiceWrapper.findAllByUserId(userId));
	}

	/**
	 * ----------------Payclip assessment requirement-4----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipSummaryResponse> sumByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(transactionClipServiceWrapper.sumByUserId(userId));
	}

	/**
	 * 
	 * ----------------Payclip assessment requirement-5----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<List<TransactionClipReport>> getReportByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(transactionClipServiceWrapper.getReportByUserId(userId));
	}

	/**
	 * ----------------Payclip assessment requirement-6----------------
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> getRandom() {
		return ResponseEntity.ok(transactionClipServiceWrapper.findRandom());
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> getById(@PathVariable String id) {
		return ResponseEntity.ok(transactionClipServiceWrapper.findById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Page<TransactionClipResponse>> getAll(Pageable pageable) {
		return ResponseEntity.ok(transactionClipServiceWrapper.findAll(pageable));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> create(@RequestBody TransactionClipRequest transactionRequest) {
		return new ResponseEntity<>(transactionClipServiceWrapper.create(transactionRequest), HttpStatus.CREATED);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<TransactionClipResponse> update(@RequestBody TransactionClipRequest transactionRequest,
			@PathVariable String id) {
		return ResponseEntity.ok(transactionClipServiceWrapper.update(transactionRequest, id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseEntity<Void> delete(@PathVariable String id) {
		transactionClipServiceWrapper.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}