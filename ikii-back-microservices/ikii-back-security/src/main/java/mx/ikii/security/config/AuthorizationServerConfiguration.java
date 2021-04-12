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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * ******************************
 * * 	AUTHORIZATION SERVER	*
 * ******************************
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
	 * Configure the security of the Authorization Server, which means in practical terms the /oauth/token endpoint. The
	 * /oauth/authorize endpoint also needs to be secure, but that is a normal user-facing endpoint and should be
	 * secured the same way as the rest of your UI, so is not covered here. The default settings cover the most common
	 * requirements, following recommendations from the OAuth2 spec, so you don't need to do anything here to get a
	 * basic server up and running.
	 * <br> <br>
	 * tokenKeyAccess : es el endpoint para generar/autenticar el token con la ruta -> POST: /oauth/token
	 * 		debe ser publico, cualquier cliente debe poder acceder a esta ruta para generar el token "permitAll()"
	 * <br> <br>
	 * checkTokenAccess : se encarga de validar el token y requiere autenticacion "isAuthenticated()"
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}
	
	/**
	 * Authorization Server.
	 * <br>
	 * Configure Client (Web App, Mobile, etc...)
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
	 * @param endpoints the endpoints configurer
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		/** Una vez agregado la informacion extra(correo, nombre, apellido) al token 
		 * 
		 *  El siguiente paso es Configurar el AuthorizationServer, 
		 * 	para esto debemos acoplar la informacion extra(correo, nombre, apellido)
		 * 	 con el access_token, token_type, refresh_token, expires_in, scope.
		 * Para eso lo hacemos en el {@link AuthorizationServerConfig}
		 * 		.tokenEnhancer(tokenEnhancerChain);
		 */
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
	 * @see accessTokenConverter() el cual se encarga de guardar los datos del usuario en el token 
	 * 	y cualquier tipo de informacion que deseamos agregar, se le conoce como los "Claims",
	 * 	por detras el componente AccessTokenConverter se encarga de tomar estos datos y 
	 * 	convertirlos en el token JWT condificados en Base64
	 * 
	 * --------------------------------------------------------
	 * Helper that translates between JWT encoded token values and OAuth authentication
	 * information (in both directions). Also acts as a {@link TokenEnhancer} when tokens are
	 * granted.
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		/** En el Servidor de Autorización agregamos la key para firmar el token,
		 * 	luego esta misma key la vamos a ocupar en el Servidor de Recursos para validar
		 * 	que sea correcto.
		 */
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}
	
}
