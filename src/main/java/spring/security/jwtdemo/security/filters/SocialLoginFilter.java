package spring.security.jwtdemo.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import spring.security.jwtdemo.dto.SocialLoginDto;
import spring.security.jwtdemo.security.tokens.PreAuthorizationToken;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SocialLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    protected SocialLoginFilter(String defaultUrl) {
        super(defaultUrl);
    }

    public SocialLoginFilter(String defaultUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        SocialLoginDto dto = objectMapper.readValue(request.getReader(), SocialLoginDto.class);
        PreAuthorizationToken token = new PreAuthorizationToken(dto);
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        successHandler.onAuthenticationSuccess(request, response, authResult);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
