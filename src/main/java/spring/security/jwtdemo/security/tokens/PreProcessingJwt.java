package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreProcessingJwt extends UsernamePasswordAuthenticationToken {

    private PreProcessingJwt(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public PreProcessingJwt(String token) {
        this(token, token.length()); // shouldn't credentials be something of more significance?
    }

    public String getTokenString() {
        return (String) super.getPrincipal();
    }
}
