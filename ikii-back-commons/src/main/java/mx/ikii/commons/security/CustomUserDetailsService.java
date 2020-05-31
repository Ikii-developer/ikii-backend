package mx.ikii.commons.security;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.ikii.commons.feignclient.service.impl.ICustomerFeignService;
import mx.ikii.commons.persistence.collection.Customer;
import mx.ikii.commons.persistence.collection.Privilege;
import mx.ikii.commons.persistence.collection.Role;
import mx.ikii.commons.utils.Nullable;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ICustomerFeignService customerFeignService;

	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		Customer userByName = customerFeignService.getByPhoneNumber(phoneNumber);
		if (Nullable.isNull(userByName)) {
			throw new UsernameNotFoundException("PhoneNumber " + phoneNumber + " not found.");
		}
		if (userByName.getRoles() == null || userByName.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("User not authorized.");
		}
		return new User(userByName.getPhoneNumber(), userByName.getPassword(), userByName.getIsEnabled(), true, true, true,
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