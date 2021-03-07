package mx.ikii.security.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 
 * ******************************
 * * 	AUTHORIZATION SERVER	*
 * ******************************
 * An authentication server is used to verify credentials when a person or another server needs to prove who they are to an application.
 * 		providing the tokens[refresh-token as well as access-token]. 
 * It also contains information about registered clients and possible access scopes and grant types. 
 * The token store is used to store the token. We will be using an in-memory token store.
 */
@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

	@Value("${config.security.oauth.client.id}")
	private String clientId;
	
	@Value("${config.security.oauth.client.secret}")
	private String clientSecret;
	
	@Value("${config.security.oauth.jwt.access-token-vs}")
	private String tokenValidatySeconds;
	
	@Value("${config.security.oauth.jwt.refresh-token-vs}")
	private String refreshTokenValidatySeconds;
	
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAdicionalToken;
	

	/**
	 * Configure the security of the Authorization Server, which means in practical terms the /oauth/token endpoint. 
	 * 
	 * The /oauth/authorize endpoint also needs to be secure, but that is a normal user-facing endpoint and should be
	 * secured the same way as the rest of your UI, so is not covered here. The default settings cover the most common
	 * requirements, following recommendations from the OAuth2 spec, so you don't need to do anything here to get a
	 * basic server up and running.
	 * 
	 * Estos dos endpoints estan protegidos por Http Basic
	 * 			Header Authorization Basic: client_id : client_secret
	 * 								 'Basic'+Base64.encode('ikiiAppMobile' + ':' + 'SecretKeyToGenJWTs')
	 * 		se envian en el Header de la peticion cuando se realiza la autenticacion dentro de autorization 
	 * 		pero del tipo Basic, a diferencia del token que se envia como Barer
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		/* tokenKeyAccess : es el endpoint para generar/autenticar el token con la ruta -> POST: /oauth/token
	 	 * 		debe ser publico
	 	 * checkTokenAccess : se encarga de validar el token y requiere autenticacion "isAuthenticated()"
		 */
		security
			.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	
	/**
	 * Client Configuration:
	 * 
	 * Configure the {@link ClientDetailsService}, e.g. declaring individual clients and their properties. 
	 * Note that password grant is not enabled (even if some clients are allowed it) unless an {@link AuthenticationManager} is
	 * supplied to the {@link #configure(AuthorizationServerEndpointsConfigurer)}. At least one client, or a fully
	 * formed custom {@link ClientDetailsService} must be declared or the server will not start.
	 * 
	 * @param clients the client details configurer
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(clientId)
				.secret(passwordEncoder.encode(clientSecret))
				.scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(Integer.parseInt(tokenValidatySeconds))
				.refreshTokenValiditySeconds(Integer.parseInt(refreshTokenValidatySeconds));
	}
	
	
	/**
	 * Configure the non-security features of the Authorization Server endpoints, like token store, token
	 * customizations, user approvals and grant types. You shouldn't need to do anything by default, unless you need
	 * password grants, in which case you need to provide an {@link AuthenticationManager}.
	 * 
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdicionalToken, accessTokenConverter()));
		
		//Registramos el authenticationManager
		endpoints.authenticationManager(authenticationManager)
			.tokenStore(tokenStore())
			.accessTokenConverter(accessTokenConverter())
			.tokenEnhancer(tokenEnhancerChain);
	}

	/**
	 * TokenStore se encarga de guardar/generar el token con los datos del accesTokenconverter.
	 * 
	 * A {@link TokenStore} implementation that just reads data from the tokens themselves. Not really a store since it
	 * never persists anything, and methods like {@link #getAccessToken(OAuth2Authentication)} always return null. But
	 * nevertheless a useful tool since it translates access tokens to and from authentications. Use this wherever a
	 * {@link TokenStore} is needed, but remember to use the same {@link JwtAccessTokenConverter} instance (or one with the same
	 * verifier) as was used when the tokens were minted.
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Helper that translates between JWT encoded token values and OAuth authentication
	 * information (in both directions). Also acts as a {@link TokenEnhancer} when tokens are
	 * granted.
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		/** En el Servidor de Autorizacion agregamos la key para firmar el token, 
		 * 	luego esta misma key la vamos a ocupar en el Servidor de Recursos para validar
		 * 	que sea correcto.
		 */
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}
	
}
