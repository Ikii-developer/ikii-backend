package mx.ikii.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import mx.ikii.commons.security.CustomUserDetailsService;

/** 
 * Tenemos que registrar esta clase de evento en Spring Security,
 * inyectando la interface
*/
@Slf4j
@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	@Autowired
	private CustomUserDetailsService usuarioService;

	//Podemos obtener los datos del usuario
	@Override
	public void publishAuthenticationSuccess(Authentication authentication) {
		log.info("publishAuthenticationSuccess:::: ");
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		log.info(mensaje);
		
		//things to do when authentication success
	}

	@Override
	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		log.info("publishAuthenticationFailure:::: ");
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);

		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			UserDetails userDetails = usuarioService.loadUserByUsername(authentication.getName());
			
			//things to do when authentication failure
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
			log.info(e.getMessage());
		}

	}

}
