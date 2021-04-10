package mx.ikii.gateway.oauth;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

/**
 * ******************************
 * * 	  RESOURCE SERVER		*
 * ******************************
**OAuth2** 

1. `Autorization Server - OAuth2`
    * @EnableAuthorizationServer - extends AuthorizationServerConfigurerAdapter
    * Configuracion: client_id, secret, scopes("read", "write")
    * Generate Token (JwtTokenStore)
    * add info token (Token Enhacer)
    * Codificar y Decodificar el JWT (private_key and public_key)
2. `Resource Server - Zuul`
    * Protegemos URLs
    * Validacion del token (Signing Key)
    * Cors
3. Clients (movil, web)
4. User (data owner)
 * 
 * Si queremos actualizar la configuracion sin tener que
 * 		reiniciar podemos usar Actuator con : @RefreshScope
 * 
 * @ResourceServerConfigurerAdapter
 * Configurer interface for <code>@EnableResourceServer</code> classes. Implement this interface to adjust the access
 * rules and paths that are protected by OAuth2 security. Applications may provide multiple instances of this interface,
 * and in general (like with other Security configurers), if more than one configures the same property, then the last
 * one wins. The configurers are sorted by {@link Order} before being applied.
 */
@RefreshScope
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/*
	 * Debemos tener la misma key con que se firma el token para validar que el
	 * token sea autentico
	 */
	@Value("${config.security.oauth.jwt.key}")
	private String jwtKey; // spring-cloud-starter-config

	/**
	 *Protegemos nuestras rutas
	 *
	 * Use this to configure the access rules for secure resources. 
	 * By default all resources <i>not</i> in "/oauth/**" are protected 
	 * 	(but no specific rules about scopes are given, for instance). 
	 * You also get an {@link OAuth2WebSecurityExpressionHandler} by default.
	 * 
	 * @param http the current http filter configuration
	 * @throws Exception if there is a problem
	 */	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//			.antMatchers(HttpMethod.GET, "/healthcheck").permitAll()
			
			/* TOKEN:
			 * Ruta para generar el token con la cual nos autenticamos */
			.antMatchers("/api/security/oauth/**")
				.permitAll()
			
			.antMatchers(HttpMethod.POST,"/api/customers/sign-up")
				.permitAll()
			
			.anyRequest().authenticated();
		
		/**
		 * hasRole: Shortcut for specifying URLs require a particular role.
		 * 		 If you do not want to have "ROLE_" automatically inserted,
		 * 			the role to require (i.e. USER, ADMIN, etc).
		 * 			Note, it should not start with "ROLE_" as this is automatically inserted.
		 * 
		 * hasAnyRole: Shortcut for specifying URLs require any of a number of roles. 
		 * 		If you do not want to have "ROLE_" automatically inserted,
		 * 			the roles to require (i.e. USER, ADMIN, etc). 
		 * 			Note, it should not start with "ROLE_" as this is automatically inserted.
		 * 
		 * hasAuthority: Specify that URLs require a particular authority.
		 * 		the authority to require (i.e. ROLE_USER, ROLE_ADMIN, etc).
		 * 
		 * hasAnyAuthority: the requests require at least one of the authorities 
		 * 			(i.e. "ROLE_USER","ROLE_ADMIN" would mean either 
		 * 				"ROLE_USER" or "ROLE_ADMIN" is required).
		 * 
		 * hasIpAddress: Specify that URLs requires a specific IP Address or <a href=
	 	 * 	"https://forum.spring.io/showthread.php?102783-How-to-use-hasIpAddress&p=343971#post343971"
		 * 		>subnet</a>. 
		 * 		the ipaddress (i.e. 192.168.1.79) or local subnet (i.e. 192.168.0/24)
		 * 
		 * @see ExpressionUrlAuthorizationConfigurer
		 */
		http.cors().configurationSource(corsConfigurationSource());
	}
	
	/**
	 * Add resource-server specific properties (like a resource id).
	 * The defaults should work for many applications, 
	 * but you might want to change at least the resource id.
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore());
	}

	/**
	 * A {@link TokenStore} implementation that just reads data from the tokens
	 * themselves. 
	 * Not really a store since it never persists anything, and methods 
	 * like {@link #getAccessToken(OAuth2Authentication)} always return null. But
	 * nevertheless a useful tool since it translates access tokens to and from
	 * authentications. Use this wherever a {@link TokenStore} is needed, but
	 * remember to use the same {@link JwtAccessTokenConverter} instance (or one
	 * with the same verifier) as was used when the tokens were minted.
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * Helper that translates between JWT encoded token values and OAuth authentication
	 * information (in both directions). 
	 * Also acts as a {@link TokenEnhancer} when tokens are granted. 
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(jwtKey);
		return tokenConverter;
	}

	/**
	 * CORS (Cross-origin resource sharing) o el intercambio de recursos de origen cruzado.
	 * Cors es un mecanismo que utiliza las cabeceras http para permitir que una aplicacion cliente que recide, 
	 * 	que esta en otro servidor/dominio distinto al backend, tenga los permisos para acceder a los recursos 
	 * 	del backend, recursos protegidos, en este caso con SpringSecurity y con el servidor de recursos(Zuul)
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		//Tenemos que configurar nuestras aplicaciones clientes(origenes, http, dominio, ruta, url)
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		//corsConfig.setAllowedOrigins(Arrays.asList("","",""));//Lista de origenes
		
		//Debemos permitir los metodos http, el metodo OPTION es importante para oAuth2
		corsConfig.setAllowedMethods(Arrays.asList("POST","GET", "PUT", "DELETE", "OPTION"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));

		//Debemos pasar esta configuracion del CORS a nuestras rutas URL(endpoints)
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Indicamos que se aplique a todas las rutas /*
		source.registerCorsConfiguration("/**", corsConfig);
		
		return source;
	}

	/**
	* Tenemos que registrar otro componente para registrar un filtro de CORS para que no solo
	* 	quede configurado en SpringSecurity, tambien a nivel Global en un filtro de Spring
	* 	a toda nuestra aplicacion en general
	*/
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> filterRegistrationBean =
		 	new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filterRegistrationBean;
	}

}
