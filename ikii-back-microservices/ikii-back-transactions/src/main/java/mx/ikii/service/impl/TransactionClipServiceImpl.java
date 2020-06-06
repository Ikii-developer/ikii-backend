package mx.ikii.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.TransactionIkii;
import mx.ikii.helper.TransactionClipHelper;
import mx.ikii.repository.ITransactionClipRepository;
import mx.ikii.service.ITransactionClipService;

/**
 * This class contains the CRUD operation to the TransactionClip resource
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Service
public class TransactionClipServiceImpl implements ITransactionClipService {

	@Autowired
	private ITransactionClipRepository transactionClipRepository;

	@Override
	public TransactionIkii findById(String id) {
		Optional<TransactionIkii> transactionClip = transactionClipRepository.findById(id);
		return ThrowsException.resourceNotFound(transactionClip, id, TransactionIkii.class);
	}

	@Override
	public Page<TransactionIkii> findAll(Pageable pageable) {
		return transactionClipRepository.findAll(pageable);
	}

	@Override
	public TransactionIkii create(TransactionIkii transaction) {
		return transactionClipRepository.insert(transaction);
	}

	@Override
	public TransactionIkii update(TransactionIkii transaction, String id) {

		TransactionIkii transactionClip = transactionClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(transactionClip), id, TransactionIkii.class);

		TransactionClipHelper.setUpdateProperties(transactionClip, transaction);

		transactionClipRepository.save(transactionClip);
		return transactionClip;
	}

	@Override
	public void delete(String id) {
		TransactionIkii transactionClip = transactionClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(transactionClip), id, TransactionIkii.class);
		transactionClipRepository.deleteById(id);
	}
}
