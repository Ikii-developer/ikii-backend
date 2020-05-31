package mx.ikii.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.TransactionIkii;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface ITransactionClipService {

	TransactionIkii findById(String id);

	Page<TransactionIkii> findAll(Pageable pageable);

	TransactionIkii create(TransactionIkii transaction);

	TransactionIkii update(TransactionIkii transaction, String id);

	void delete(String id);

}
