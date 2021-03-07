package mx.ikii.customers;

//@Configuration
public class OAuth2ServerConfiguration {
	/*
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
					.permitAll();
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
	*/
}