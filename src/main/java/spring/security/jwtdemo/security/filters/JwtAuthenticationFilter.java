package spring.security.jwtdemo.security.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import spring.security.jwtdemo.security.HeaderTokenExtractor;
import spring.security.jwtdemo.security.handlers.JwtAuthenticationFailureHandler;
import spring.security.jwtdemo.security.tokens.PreProcessJwt;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    private JwtAuthenticationFailureHandler failureHandler;
    private HeaderTokenExtractor extractor;

    protected JwtAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    public JwtAuthenticationFilter(RequestMatcher matcher, JwtAuthenticationFailureHandler failureHandler, HeaderTokenExtractor extractor) {
        this(matcher);
        this.failureHandler = failureHandler;
        this.extractor = extractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = request.getHeader(AUTHORIZATION_HEADER);
        PreProcessJwt jwt = new PreProcessJwt(extractor.extract(tokenPayload));

        return super.getAuthenticationManager().authenticate(jwt);
    }

    @Override // On successful authentication
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);

        chain.doFilter(request, response);
    }

    @Override // On unsuccessful authentication
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //Security Context needs to be cleared
        SecurityContextHolder.clearContext();

        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
