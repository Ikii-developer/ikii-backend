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
	 * Configuramos el permiso que va a tener nuestros endpoints del servidor de autorizacion de oAuth2,
	 * 		para generar el token y validar.
	 * -
	 * tokenKeyAccess : es el endpoint para generar/autenticar el token con la ruta -> POST: /oauth/token
	 * 		debe ser publico, cualquier cliente debe poder acceder a esta ruta para generar el token "permitAll()"
	 * -
	 * checkTokenAccess : se encarga de validar el token y requiere autenticacion "isAuthenticated()"
	 * -
	 * 
	 * Estos dos endpoints estan protegidos por Http Basic
	 * 			Header Authorization Basic: client_id : client_secret
	 * 								 'Basic'+Base64.encode('ikiiAppMobile' + ':' + 'SecretKeyToGenJWTs')
	 * 		se envian en el Header de la peticion cuando se realiza la autenticacion dentro de autorization 
	 * 		pero del tipo Basic, a diferencia del token que se envia como Barer
	 * 
	 * ------------------------------------------------------
	 * Configure the security of the Authorization Server, 
	 * 		which means in practical terms the {@code /oauth/token} endpoint. 
	 * The {@code /oauth/authorize} endpoint also needs to be secure, but that is a normal user-facing endpoint and should be
	 * secured the same way as the rest of your UI, so is not covered here. The default settings cover the most common
	 * requirements, following recommendations from the OAuth2 spec, so you don't need to do anything here to get a
	 * basic server up and running.
	 * 
	 * @param security a fluent configurer for security features
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}
	
	/**
	 * Authorization Server
	 * 
	 * Para configurar un cliente oauth deberemos especificar:
	
	 * <b>withClient</b>:				Id del cliente
	 * 
	 * <b>secret</b>: 					Password del cliente
	 * 
	 * <b>authorizedGrantTypes</b>: 	Tipo de permisos que le permitirán realizar operaciones 
	 * 									en el proceso de autenticación oauth así como en la generación de tokens
	 * 
	 * <b>scopes</b>: 					Ámbitos o conjunto de recursos, que los usuarios que se integran vía oauth, 
	 * 									conceden al cliente para consultar sus datos.
	 * 
	 * <b>redirectUris</b>:  				Url a la que se redirige una vez el proceso de autenticación oauth haya finalizado.
	 * 
	 * <b>accessTokenValiditySeconds</b>: 	Tiempo en el que el access token es válido
	 * 
	 * <b>refreshTokenValiditySeconds</b>:	Tiempo en el que el refresh token es válido
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		/** Configuramos nuestros clientes(Android, Angular, etc...) que se van a conectar con nuestros microservicios.
		 *
		 * <b>.withClient</b>: registramos nuestro cliente el cual presenta el identificador de nuestra aplicacion
		 * 											el cual se va a conectar con los microservicios.
		 * <b>.secret</b>: password del cliente
		 * 
		 * 1-
		 * .authorizedGrantTypes : tipo de concesion que va a tener nuestra aplicacion, como vamos a obtener el token,
		 * 			en este caso sera por medio del password.
		 * 		Utilizamos password cuando es con credenciales, es decir cuando los usuarios existen en el sistema.
		 * 	
		 * Al final le vamos a enviar 4 credenciales para autenticar: withClient, secret, username y password
		 * 
		 * 2-
		 * Tambien tenemos otros tipos como <b>Authorization Code</b> el cual no es para autenticar un usuario con credenciales,
		 * 	 si no a travez de un "codigo de autorizacion" que nos entrega el backend, un redireccionamiento y a travez del
		 * 		"codigo de autorizacion" obtenemos el "token de acceso".
		 * 	Basicamente es un intercambio entre el "codigo de autorizacion" por un "token de acceso" via redireccionamiento
		 * 		de una URL, a diferencia del password que intercambiamos las "credenciales de un usuario" por "token de acceso"
		 * 
		 * 3- 
		 * Tenemos un tercer tipo "implicit", la diferencia es que enviamos el clienteID el password y automaticamente 
		 * 	vamos a recibir el token de acceso sin pasar por un codigo de autorizacion, basicamente se utiliza 
		 * 	para aplicaciones publicas que no requieran mucha seguridad. 
		 * 
		 * 4-
		 * El <b>refresh_token<b> es para poder refrescar el token de acceso
		 *  
		 * --------------------------------------------
		 * Configure the {@link ClientDetailsService}, e.g. declaring individual clients and their properties. Note that
		 * password grant is not enabled (even if some clients are allowed it) unless an {@link AuthenticationManager} is
		 * supplied to the {@link #configure(AuthorizationServerEndpointsConfigurer)}. At least one client, or a fully
		 * formed custom {@link ClientDetailsService} must be declared or the server will not start.
		 * 
		 * @param clients the client details configurer
		 */
		clients.inMemory()
			.withClient(clientId)
				.secret(passwordEncoder.encode(clientSecret))
				.scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(Integer.parseInt(tokenValidatySeconds))
				.refreshTokenValiditySeconds(Integer.parseInt(refreshTokenValidatySeconds));
		//.and()
		//.withClient("otroCliente")...(mas parametros);
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
		/* Este recibe como argumento en el constructor el "accessTokenConverter()"
		 * Ya que para poder crear el token y almacenarlo,
		 * 	necesitamos el componente "accessTokenConverter()" que se encarga de convertir 
		 * 	el token con todos los datos
		*/	
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
		/** En el Servidor de Autorizacion agregamos la key para firmar el token, 
		 * 	luego esta misma key la vamos a ocupar en el Servidor de Recursos para validar
		 * 	que sea correcto.
		 */
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}
	
}
