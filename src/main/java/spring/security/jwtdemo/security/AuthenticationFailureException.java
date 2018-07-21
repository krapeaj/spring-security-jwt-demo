package spring.security.jwtdemo.security;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailureException extends AuthenticationException {

    public AuthenticationFailureException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthenticationFailureException(String msg) {
        super(msg);
    }
}
