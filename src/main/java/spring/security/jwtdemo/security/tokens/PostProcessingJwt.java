package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import spring.security.jwtdemo.domain.UserRole;

import java.util.Collection;

public class PostProcessingJwt extends UsernamePasswordAuthenticationToken {

    private PostProcessingJwt(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public PostProcessingJwt(String userId, UserRole userRole) {
        super(userId, "1234", userRole.toList());
    }

    public String getUserId() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
