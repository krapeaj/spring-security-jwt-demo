package spring.security.jwtdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.UserRole;
import spring.security.jwtdemo.security.tokens.PreProcessingJwt;

import java.util.*;
import java.util.stream.Collectors;

/*
This object is used in the context of security instead of the domain Account object.
 */

public class AccountContext extends User {
    private static final String ARBITRARY_PASSWORD = "JWT verified";

    private AccountContext(String userId, String password, Collection<? extends GrantedAuthority> authorities) {
        super(userId, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account.getUserId(), account.getPassword(), account.getUserRole().toList());
    }

    public static AccountContext fromDecodedJwtPayload(String userId, String userRole) {
        return new AccountContext(userId, ARBITRARY_PASSWORD,  UserRole.getUserRole(userRole).toList());
    }

    public String getAuthority() {
        return getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();
    }
}
