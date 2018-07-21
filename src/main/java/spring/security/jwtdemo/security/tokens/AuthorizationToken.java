package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class AuthorizationToken extends UsernamePasswordAuthenticationToken {

    public AuthorizationToken(String userId, String password) {
        super(userId, password);
    }

    public AuthorizationToken(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public String getUserId() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }

    public List<GrantedAuthority> getAuthrities() {
        return (List<GrantedAuthority>) super.getAuthorities();
    }
}
