package mx.ikii.security.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import feign.FeignException;
import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;

@Service
@Transactional
public class UsuarioService implements UserDetailsService, IUsuarioService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private ICustomerFeignService customerFeignService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer userByName = null;
		try {
			LOGGER.info("******************************* CustomUserDetailsService {}", email);
			userByName = customerFeignService.getByEmail(email);
			
			return new User(userByName.getEmail(), userByName.getPassword(), userByName.isEnabled(), 
					userByName.isCredentialNonExpired(), userByName.isCredentialNonExpired(), userByName.isAccountNonLocked(),
					getAuthorities(userByName.getRoles()));
		} catch (FeignException e) {
			LOGGER.info("Exception message {}", e.getMessage());
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private List<String> getPrivileges(Collection<Role> roles) {
		List<Privilege> privileges = roles.stream().map(Role::getPrivileges).flatMap(Set::stream).collect(toList());
		return privileges.stream().map(Privilege::getName).collect(toList());
	}

	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		return privileges.stream().map(SimpleGrantedAuthority::new).collect(toList());
	}

	@Override
	public Customer findByUsername(String username) {
		return customerFeignService.getByEmail(username);
	}

}