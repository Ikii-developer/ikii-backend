package mx.ikii.customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2ServerConfiguration {

	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private JwtAccessTokenConverter jwtAccessTokenConverter;

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.tokenStore(new JwtTokenStore(jwtAccessTokenConverter));
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().antMatchers("/phone-numbers/**").permitAll().and()
					.authorizeRequests().antMatchers("/**").permitAll().and().authorizeRequests()
					.antMatchers("/emails/**").permitAll().and().authorizeRequests().antMatchers("/sign-up/**")
					.permitAll().and().authorizeRequests().anyRequest().authenticated();
		}
	}

	@Configuration
	@EnableAuthorizationServer
	protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private JwtAccessTokenConverter jwtAccessTokenConverter;

		@Autowired
		private AuthenticationManager authenticationManager;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
			endpoints.tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
					.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter);
		}
	}
}