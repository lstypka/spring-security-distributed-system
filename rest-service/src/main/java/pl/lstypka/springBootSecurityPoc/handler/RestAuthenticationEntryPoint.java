package pl.lstypka.springBootSecurityPoc.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import pl.lstypka.springBootSecurityPoc.dto.FaultDto;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		PrintWriter writer = response.getWriter();
		ObjectMapper mapper = new ObjectMapper();
		FaultDto faultDto = new FaultDto("SPRING-SECURITY-1", authException.getMessage());
		writer.println(mapper.writeValueAsString(faultDto));
	}
}