package mx.ikii.security.config;

import java.util.HashMap;
import java.util.Map;

import mx.ikii.security.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.persistence.collection.Customer;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;

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
		
		Customer usuario = usuarioService.findByUsername(authentication.getName());
		info.put("username", usuario.getEmail());
		info.put("enabled", usuario.getIsEnabled());
		
		//Casteamos a la implementacion 
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		/**
		 *  El siguiente paso es Configurar el AuthorizationServer, 
		 * 	para esto debemos acoplar la informacion extra(correo, nombre, apellido)
		 * 	 con el access_token, token_type, refresh_token, expires_in, scope.
		 * Para eso lo hacemos en el {@link AuthorizationServerConfig}
		 * 		.tokenEnhancer(tokenEnhancerChain);
		 */
		
		return accessToken;
	}

}
