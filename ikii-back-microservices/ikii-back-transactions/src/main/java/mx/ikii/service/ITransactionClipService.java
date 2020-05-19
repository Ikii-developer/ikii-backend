package mx.ikii.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.TransactionClip;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface ITransactionClipService {

	TransactionClip findById(String id);

	Page<TransactionClip> findAll(Pageable pageable);

	TransactionClip create(TransactionClip transaction);

	TransactionClip update(TransactionClip transaction, String id);

	void delete(String id);

}
