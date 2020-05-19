package mx.ikii.users.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.exception.handler.helper.ThrowsException;
import mx.ikii.commons.persistence.collection.UserClip;
import mx.ikii.users.helper.UserClipHelper;
import mx.ikii.users.repository.IUserClipRepository;
import mx.ikii.users.service.IUserClipService;

/**
 * This class contains the CRUD operations related to the user resource
 * 
 * @author Arturo Isaac Velázquez Vargas
 *
 */
@Service
public class UserClipServiceImpl implements IUserClipService {

	@Autowired
	private IUserClipRepository userClipRepository;

	@Override
	public UserClip findById(String id) {
		Optional<UserClip> userClip = userClipRepository.findById(id);
		return ThrowsException.resourceNotFound(userClip, id, UserClip.class);
	}

	@Override
	public UserClip findByUserName(String userName) {
		UserClip userClip = userClipRepository.findByUserName(userName);
		return userClip;
	}

	@Override
	public Page<UserClip> findAll(Pageable pageable) {
		return userClipRepository.findAll(pageable);
	}

	@Override
	public UserClip create(UserClip user) {
		return userClipRepository.insert(user);
	}

	@Override
	public UserClip update(UserClip user, String id) {

		UserClip userClip = userClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(userClip), id, UserClip.class);

		UserClipHelper.setUpdateProperties(userClip, user);
		userClipRepository.save(userClip);
		return userClip;
	}

	@Override
	public void delete(String id) {
		UserClip userClip = userClipRepository.findById(id).orElse(null);
		ThrowsException.resourceNotFound(Optional.ofNullable(userClip), id, UserClip.class);
		userClipRepository.deleteById(id);
	}

}
