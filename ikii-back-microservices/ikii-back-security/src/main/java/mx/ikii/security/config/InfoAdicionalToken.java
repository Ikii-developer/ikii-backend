package mx.ikii.security.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.persistence.collection.Customer;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private ICustomerFeignService customerFeignService;

	/**
	 * Provides an opportunity for customization of an access token (e.g. through its additional information map) during
	 * the process of creating a new token for use by a client.
	 * 
	 * @param accessToken the current access token with its expiration and refresh token
	 * @param authentication the current authentication including client and user details
	 * @return a new token enhanced with additional information
	 */
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> info = new HashMap<String, Object>();

		Customer customer = customerFeignService.getByEmailForAuth(authentication.getName()); 
		info.put("id", customer.getId());
		info.put("username", customer.getEmail());
		info.put("name", customer.getName());
		info.put("lastNAme", customer.getLastName());
		info.put("secondLastName", customer.getSecondLastName());
		info.put("phoneNumber", customer.getPhoneNumber());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);


		return accessToken;
	}

}
