package pl.lstypka.springSecurityDistributedSystem.config.config;
/**
 * Created by Lukasz Stypka on 2015-11-20.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;
import pl.lstypka.springSecurityDistributedSystem.config.filter.CustomUsernamePasswordAuthenticationFilter;
import pl.lstypka.springSecurityDistributedSystem.config.handler.AuthenticationFailureHandler;
import pl.lstypka.springSecurityDistributedSystem.config.handler.AuthenticationSuccessHandler;
import pl.lstypka.springSecurityDistributedSystem.config.handler.RestAuthenticationEntryPoint;
import pl.lstypka.springSecurityDistributedSystem.config.service.AuthService;

@EnableRedisHttpSession
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String AUTHENTICATE_ENDPOINT = "/authenticate";

    // Beans connected with translating input and output to JSON
    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler();
    }

    @Bean
    RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(AUTHENTICATE_ENDPOINT, "POST"));
        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        authFilter.setUsernameParameter("j_username");
        authFilter.setPasswordParameter("j_password");
        return authFilter;
    }

    // Bean responsible for getting information about user details
    @Bean
    AuthService authService() {
        return new AuthService();
    }

    // These beans are required to sharing session tokens (in the redis database) between applications
    @Bean
    public JedisConnectionFactory connectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint()).and().addFilterBefore(authenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class).csrf().disable()
                .authorizeRequests().antMatchers("/**").authenticated().and().formLogin().loginProcessingUrl(AUTHENTICATE_ENDPOINT).failureHandler(authenticationFailureHandler())
                .successHandler(authenticationSuccessHandler()).and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService());
    }

}