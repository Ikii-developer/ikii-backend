package mx.ikii.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.TransactionClip;
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
	public TransactionClip findById(String id) {
		Optional<TransactionClip> transactionClip = transactionClipRepository.findById(id);
		return ThrowsException.resourceNotFound(transactionClip, id, TransactionClip.class);
	}

	@Override
	public Page<TransactionClip> findAll(Pageable pageable) {
		return transactionClipRepository.findAll(pageable);
	}

	@Override
	public TransactionClip create(TransactionClip transaction) {
		return transactionClipRepository.insert(transaction);
	}

	@Override
	public TransactionClip update(TransactionClip transaction, String id) {

		TransactionClip transactionClip = transactionClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(transactionClip), id, TransactionClip.class);

		TransactionClipHelper.setUpdateProperties(transactionClip, transaction);

		transactionClipRepository.save(transactionClip);
		return transactionClip;
	}

	@Override
	public void delete(String id) {
		TransactionClip transactionClip = transactionClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(transactionClip), id, TransactionClip.class);
		transactionClipRepository.deleteById(id);
	}
}
