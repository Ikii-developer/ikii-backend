package mx.ikii.users.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import mx.ikii.commons.persistence.collection.UserClip;

/**
 * This interface contains the methods related to CRUD operations
 * 
 * @author Arturo Isaac Velazqeuz Vargas
 *
 */
public interface IUserClipService {

	UserClip findById(String id);

	UserClip findByUserName(String userName);

	Page<UserClip> findAll(Pageable pageable);

	UserClip create(UserClip transaction);

	UserClip update(UserClip transaction, String id);

	void delete(String id);

}
