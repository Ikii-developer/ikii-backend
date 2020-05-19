package mx.ikii.commons.feignclient.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mx.ikii.commons.payload.request.user.UserClipRequest;
import mx.ikii.commons.payload.response.user.UserClipResponse;

/**
 * This class is used as the feign client class to interact internally with the
 * user microservice
 * 
 * @author Arturo Isaac Velazquez Vargas
 *
 */
@FeignClient(name = "users")
public interface IUserClipFeignClientRepository {

	@GetMapping("users/{id}")
	ResponseEntity<UserClipResponse> getById(@PathVariable("id") String id);

	@GetMapping("users/usernames/{userName}")
	ResponseEntity<UserClipResponse> getByUserName(@PathVariable("userName") String userName);

	@GetMapping("users/")
	ResponseEntity<Page<UserClipResponse>> getAll(Pageable pageable);

	@PutMapping("users/{id}")
	ResponseEntity<UserClipResponse> update(@RequestBody UserClipRequest userRequest, @PathVariable("id") String id);

}
