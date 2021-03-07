package mx.ikii.payment;

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
			http.csrf().disable().authorizeRequests().and().authorizeRequests().anyRequest().authenticated();
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