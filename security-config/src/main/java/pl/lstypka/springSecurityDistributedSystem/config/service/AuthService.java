package pl.lstypka.springSecurityDistributedSystem.config.service;

import com.google.common.collect.Lists;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.lstypka.springSecurityDistributedSystem.config.bo.SecurityUser;
import pl.lstypka.springSecurityDistributedSystem.config.dto.UserDto;
import pl.lstypka.springSecurityDistributedSystem.config.exception.UserNotFoundException;

import java.util.stream.Collectors;

@Service("authService")
public class AuthService implements UserDetailsService {

	@Override
	public SecurityUser loadUserByUsername(String username) {
		if("admin".equals(username)) {
			return new SecurityUser(1L, username, "s3cr3t", Lists.newArrayList(()-> "ROLE_ADMIN"));
		}
		if("user".equals(username)) {
			return new SecurityUser(1L, username, "s3cr3t", Lists.newArrayList(()-> "ROLE_USER"));
		}

		throw new UserNotFoundException("User %s not found".format(username));
	};

	public UserDto getLoggedUser() {
		SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new UserDto(securityUser.getUserNo(), securityUser.getUsername(), securityUser.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));
	};

}