package spring.security.jwtdemo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.UserRole;

import java.util.*;
import java.util.stream.Collectors;

/*
This object is used in the context of security instead of the domain Account object.
 */

public class AccountContext extends User {

    private AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account.getUserId(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole userRole) {
        return Arrays.asList(userRole).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public String getAuthority() {
        return getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().get();
    }
}
