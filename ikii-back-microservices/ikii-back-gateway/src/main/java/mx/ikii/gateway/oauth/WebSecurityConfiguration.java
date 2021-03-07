package mx.ikii.gateway.oauth;

//@EnableWebSecurity
public class WebSecurityConfiguration { //extends WebSecurityConfigurerAdapter {
	
	/*
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/// @formatter:off
        httpSecurity
                .csrf().disable()
                .logout().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().anonymous()
                .and().exceptionHandling().authenticationEntryPoint(
                            (req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                    .antMatchers("/security").permitAll()
                    .and().authorizeRequests().antMatchers("customers/sign-up").permitAll();
     // @formatter:on
	}
	*/
}
