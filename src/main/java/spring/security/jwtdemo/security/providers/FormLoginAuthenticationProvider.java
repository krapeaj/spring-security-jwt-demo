package spring.security.jwtdemo.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.security.AccountContext;
import spring.security.jwtdemo.security.AccountContextService;
import spring.security.jwtdemo.security.AuthenticationFailureException;
import spring.security.jwtdemo.security.tokens.PostAuthorizationToken;
import spring.security.jwtdemo.security.tokens.PreAuthorizationToken;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountContextService accountContextService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthorizationToken token = (PreAuthorizationToken) authentication;
        String userId = token.getUserId();
        String password = token.getPassword();

        // Compare pre-authorization credentials (ie. userId & password) to that of actual in DB
        Account account = accountContextService.findByUserId(userId);
        if (isCorrectPassword(account, password)) {
            return PostAuthorizationToken.fromAccountContext(AccountContext.fromAccountModel(account));
        }
        throw new AuthenticationFailureException("사용자 인증에 실패했습니다.");
    }

    @Override
    public boolean supports(Class<?> authentication) { // PreAuthorizationToken object will be caught by this provider.
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPassword(Account account, String password) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
