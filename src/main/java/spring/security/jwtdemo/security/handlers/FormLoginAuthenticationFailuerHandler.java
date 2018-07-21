package spring.security.jwtdemo.security.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
This object handles form login failure.
It should have appropriate logic that provides client with necessary information on what to do.
 */

@Component
public class FormLoginAuthenticationFailuerHandler implements AuthenticationFailureHandler {
    private static final Logger logger = LoggerFactory.getLogger(FormLoginAuthenticationFailuerHandler.class);

    @Override // This is not an 'appropriate' handling..
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.error(exception.getMessage());
    }
}
