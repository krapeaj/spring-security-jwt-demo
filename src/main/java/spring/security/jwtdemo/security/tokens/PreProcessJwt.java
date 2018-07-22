package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreProcessJwt extends UsernamePasswordAuthenticationToken {

    private PreProcessJwt(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PreProcessJwt(String token) {
        this(token, token.length()); // ??
    }
}
