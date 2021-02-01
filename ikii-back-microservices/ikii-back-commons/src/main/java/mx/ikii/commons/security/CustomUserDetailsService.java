package mx.ikii.commons.security;

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
import mx.ikii.commons.utils.Nullable;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	private ICustomerFeignService customerFeignService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer userByName = null;
		try {
			LOGGER.info("Customer email {}", email);
			userByName = customerFeignService.getByEmailForAuth(email);
		} catch (FeignException e) {
			throw new UsernameNotFoundException("Email " + email + " not found.");
		}
		if (Nullable.isNull(userByName)) {
			throw new UsernameNotFoundException("Email " + email + " not found.");
		}
		return new User(userByName.getEmail(), userByName.getPassword(), userByName.getIsEnabled(), true, true, true,
				getAuthorities(userByName.getRoles()));
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

}