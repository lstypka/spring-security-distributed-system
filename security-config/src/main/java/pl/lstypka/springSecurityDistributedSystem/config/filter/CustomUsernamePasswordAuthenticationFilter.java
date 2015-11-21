package pl.lstypka.springSecurityDistributedSystem.config.filter;

/**
 * Created by Łukasz on 2015-11-20.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.lstypka.springSecurityDistributedSystem.config.dto.LoginRequestDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final static String USERNAME = "user";
	private final static String PASSWORD = "password";
	private final static String CONTENT_TYPE = "Content-Type";

	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if (request.getHeader(CONTENT_TYPE).contains("application/json")) {
			return (String) request.getAttribute(PASSWORD);
		} else {
			return super.obtainPassword(request);
		}
	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if (request.getHeader(CONTENT_TYPE).contains("application/json")) {
			return (String) request.getAttribute(USERNAME);
		} else {
			return super.obtainUsername(request);
		}
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		if (request.getHeader(CONTENT_TYPE).contains("application/json")) {
			try {
				/*
				 * HttpServletRequest can be read only once
				 */
				StringBuffer sb = new StringBuffer();
				String line = null;

				BufferedReader reader = request.getReader();
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				// json transformation
				ObjectMapper mapper = new ObjectMapper();
				LoginRequestDto loginRequest = mapper.readValue(sb.toString(), LoginRequestDto.class);

				// persist user and password as request attribute
				request.setAttribute(USERNAME, loginRequest.getUser());
				request.setAttribute(PASSWORD, loginRequest.getPassword());
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		return super.attemptAuthentication(request, response);
	}
}