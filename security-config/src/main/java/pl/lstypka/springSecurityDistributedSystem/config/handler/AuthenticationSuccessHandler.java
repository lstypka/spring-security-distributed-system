package pl.lstypka.springSecurityDistributedSystem.config.handler;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import pl.lstypka.springSecurityDistributedSystem.config.bo.SecurityUser;
import pl.lstypka.springSecurityDistributedSystem.config.dto.UserDto;

public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		SecurityUser userSecurity =  (SecurityUser) authentication.getPrincipal();
		ObjectMapper mapper = new ObjectMapper();
		PrintWriter writer = response.getWriter();

		UserDto userDto = new UserDto(userSecurity.getUserNo(), userSecurity.getUsername(), userSecurity.getAuthorities().stream().map(x -> x.getAuthority()).collect(Collectors.toList()));

		mapper.writeValue(writer, userDto);
		writer.flush();
	}
}