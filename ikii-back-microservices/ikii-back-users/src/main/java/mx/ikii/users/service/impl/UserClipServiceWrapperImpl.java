package mx.ikii.users.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import mx.ikii.commons.mapper.user.IUserClipMapper;
import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.commons.persistence.collection.UserClip;
import mx.ikii.commons.utils.PageHelper;
import mx.ikii.users.repository.IUserPrivilegeRepository;
import mx.ikii.users.repository.IUserRoleRepository;
import mx.ikii.users.service.IUserClipService;
import mx.ikii.users.service.IUserClipServiceWrapper;

/**
 * 
 * This class acts as a wrapper layer to the business logic in the UserClip
 * service
 * 
 * @author Arturo Isaac Velázquez Vargas
 * @see <a href=
 *      "https://github.com/cesaralcancio/simple-test/blob/master/README.md">payclip_assessment</a>
 * 
 */
@Service
public class UserClipServiceWrapperImpl implements IUserClipServiceWrapper {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IUserClipMapper userClipMapper;

	@Autowired
	private IUserClipService userClipService;

	@Autowired
	private IUserPrivilegeRepository userPrivilegeRepository;

	@Autowired
	private IUserRoleRepository userRoleRepository;

	@Override
	public UserClipResponse findById(String id) {
		return userClipMapper.entityToResponse(userClipService.findById(id));
	}

	@Override
	public UserClipResponse findByUseName(String userName) {
		return userClipMapper.entityToResponse(userClipService.findByUserName(userName));
	}

	@Override
	public Page<UserClipResponse> findAll(Pageable pageable) {
		Page<UserClip> userClip = userClipService.findAll(pageable);
		List<UserClipResponse> usersResponse = userClipMapper.entityToResponse(userClip.getContent());

		return PageHelper.createPage(usersResponse, pageable, userClip.getTotalElements());
	}

	@Override
	public UserClipResponse create(UserClipRequest userRequest) {
		UserClip userEntity = userClipMapper.requestToEntity(userRequest);
		userEntity.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		return userClipMapper.entityToResponse(userClipService.create(userEntity));
	}

	@Override
	public UserClipResponse update(UserClipRequest userRequest, String id) {
		UserClip userEntity = userClipMapper.requestToEntity(userRequest);
		return userClipMapper.entityToResponse(userClipService.update(userEntity, id));
	}

	@Override
	public void delete(String id) {
		userClipService.delete(id);
	}

	@Override
	public Role create(Role role) {
		return userRoleRepository.save(role);
	}

	@Override
	public Privilege create(Privilege privilege) {
		return userPrivilegeRepository.save(privilege);
	}

	@Override
	public void assignPrivilege(String roleId, String privilegeId) {
		Optional<Role> userRole = userRoleRepository.findById(roleId);
		if (userRole.isPresent()) {
			Optional<Privilege> userPrivilege = userPrivilegeRepository.findById(privilegeId);
			if (userPrivilege.isPresent()) {
				Role roleToUpdate = userRole.get();
				roleToUpdate.getPrivileges().add(userPrivilege.get());
				userRoleRepository.save(roleToUpdate);
			}
		}
	}

	@Override
	public void assignRole(String userId, String roleId) {
		UserClip user = userClipService.findById(userId);
		Optional<Role> userRole = userRoleRepository.findById(roleId);
		if (userRole.isPresent()) {
			user.getRoles().add(userRole.get());
			userClipService.update(user, user.getId());

		}
	}

}
