package spring.security.jwtdemo.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.AccountRepository;
import spring.security.jwtdemo.security.AccountContext;
import spring.security.jwtdemo.security.services.SocialFetchService;
import spring.security.jwtdemo.security.tokens.PostAuthorizationToken;
import spring.security.jwtdemo.security.tokens.SocialPreAuthorizationToken;
import spring.security.jwtdemo.social.SocialUserProperty;

@Component
public class SocialLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository repository;

    @Qualifier("socialFetchServiceProd")
    @Autowired
    private SocialFetchService service;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SocialPreAuthorizationToken token = (SocialPreAuthorizationToken) authentication;
        SocialUserProperty property = service.getSocialUserInfo(token.getSocialLoginDto());
        Long socialId = property.getSocialUniqueId();
        String userId = property.getNickname();
        Account account = repository.findBySocialIdAndUserId(socialId, userId)
                .orElseGet(() -> repository.save(new Account(userId, socialId)));
        return PostAuthorizationToken.fromAccountContext(AccountContext.fromAccountModel(account));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SocialPreAuthorizationToken.class.isAssignableFrom(authentication);
    }
}
