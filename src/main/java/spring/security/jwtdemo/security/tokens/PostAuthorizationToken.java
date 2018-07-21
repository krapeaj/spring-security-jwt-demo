package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import spring.security.jwtdemo.security.AccountContext;

import java.util.Collection;

/*
This is a Post- authorization token object, which is created after authorization has been confirmed by a provider.
 */

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PostAuthorizationToken(AccountContext accountContext, String password, Collection<? extends GrantedAuthority> authorities) {
        super(accountContext, password, authorities);
    }

    public static PostAuthorizationToken fromAccountContext(AccountContext accountContext) {
        return new PostAuthorizationToken(accountContext, accountContext.getPassword(), accountContext.getAuthorities());
    }
}
