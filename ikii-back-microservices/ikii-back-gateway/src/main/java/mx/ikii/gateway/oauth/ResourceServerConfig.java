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

			.antMatchers("/api/security/oauth/**")
				.permitAll()

			.antMatchers(HttpMethod.POST,"/api/customers/sign-up")
				.permitAll()

			.anyRequest().authenticated();

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

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.addAllowedOrigin("*");
		//corsConfig.setAllowedOrigins(Arrays.asList("","",""));//Lista de origenes

		corsConfig.setAllowedMethods(Arrays.asList("POST","GET", "PUT", "DELETE", "OPTION"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization","Content-Type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
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