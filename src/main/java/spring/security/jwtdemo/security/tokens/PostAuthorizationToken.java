package spring.security.jwtdemo.security.tokens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import spring.security.jwtdemo.security.AccountContext;

import java.util.Collection;

/*
This is a Post- authorization token object, which is created after authorization has been confirmed by a provider.
 */

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {
    private static final Logger logger = LoggerFactory.getLogger(PostAuthorizationToken.class);

    private AccountContext accountContext;

    private PostAuthorizationToken(AccountContext accountContext) {
        super(accountContext.getUsername(), accountContext.getPassword(), accountContext.getAuthorities());
        logger.error("Account Context. UserId: {}, Password: {}", accountContext.getUsername(), accountContext.getPassword());
        this.accountContext = accountContext;
    }

    public static PostAuthorizationToken fromAccountContext(AccountContext accountContext) {
        return new PostAuthorizationToken(accountContext);
    }

    public AccountContext getAccountContext() {
        return accountContext;
    }
}
