package pl.lstypka.springBootSecurityPoc.service;

import com.google.common.collect.Lists;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lstypka.springBootSecurityPoc.bo.SecurityUser;
import pl.lstypka.springBootSecurityPoc.exception.UserNotFoundException;

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

}