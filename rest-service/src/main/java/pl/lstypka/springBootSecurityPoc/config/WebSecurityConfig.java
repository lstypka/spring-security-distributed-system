package pl.lstypka.springBootSecurityPoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.lstypka.springBootSecurityPoc.filter.CustomUsernamePasswordAuthenticationFilter;
import pl.lstypka.springBootSecurityPoc.handler.AuthenticationFailureHandler;
import pl.lstypka.springBootSecurityPoc.handler.AuthenticationSuccessHandler;
import pl.lstypka.springBootSecurityPoc.handler.RestAuthenticationEntryPoint;
import pl.lstypka.springBootSecurityPoc.service.AuthService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private AuthService authService;

    @Bean
    public CustomUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authFilter = new CustomUsernamePasswordAuthenticationFilter();
        authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/authenticate", "POST"));
        authFilter.setAuthenticationManager(super.authenticationManager());
        authFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        authFilter.setUsernameParameter("j_username");
        authFilter.setPasswordParameter("j_password");
        return authFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and().addFilterBefore(authenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class).csrf().disable()
                .authorizeRequests().antMatchers("/**").authenticated().and().formLogin().loginProcessingUrl("/authenticate").failureHandler(authenticationFailureHandler)
                .successHandler(authenticationSuccessHandler).and().logout();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService);
    }
}