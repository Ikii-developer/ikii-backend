package mx.ikii.commons.feignclient.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mx.ikii.commons.feignclient.repository.IUserClipFeignClientRepository;
import mx.ikii.commons.mapper.user.IUserClipMapper;
import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;
import mx.ikii.commons.persistence.collection.UserClip;
import mx.ikii.commons.utils.ResponseEntityHelper;

/**
 * @author Arturo Isaac Velazquez Vargas
 */
@Service
public class UserClipFeignServiceImpl implements IUserClipFeignService {

	@Autowired
	private IUserClipFeignClientRepository userClipFeignClientRepository;

	@Autowired
	private IUserClipMapper userClipMapper;

	@Override
	public UserClip getById(String id) {
		return userClipMapper
				.responseToEntity(ResponseEntityHelper.processingHttpStatus(userClipFeignClientRepository.getById(id)));
	}

	@Override
	public UserClip update(UserClipRequest userRequest, String id) {
		return userClipMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(userClipFeignClientRepository.update(userRequest, id)));
	}

	@Override
	public UserClip getByUserName(String userName) {
		return userClipMapper.responseToEntity(
				ResponseEntityHelper.processingHttpStatus(userClipFeignClientRepository.getByUserName(userName)));
	}
}
