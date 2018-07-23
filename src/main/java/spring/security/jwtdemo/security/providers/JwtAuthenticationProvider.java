package spring.security.jwtdemo.security.providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.security.jwtdemo.domain.UserRole;
import spring.security.jwtdemo.security.AccountContext;
import spring.security.jwtdemo.security.JwtUtils;
import spring.security.jwtdemo.security.tokens.PostProcessingJwt;
import spring.security.jwtdemo.security.tokens.PreProcessingJwt;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreProcessingJwt jwt = (PreProcessingJwt) authentication;
        // Decode token using decoder (need to create method in jwt utils which returns account context) and verify token payload.
        logger.error("TOKEN STRING: {}", jwt.getTokenString());
        AccountContext accountContext = JwtUtils.decodeJwt(jwt.getTokenString());
        // If verified, return PostProcessingJwt.
        return new PostProcessingJwt(accountContext.getUsername(), UserRole.getUserRole(accountContext.getAuthority()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreProcessingJwt.class.isAssignableFrom(authentication);
    }
}
