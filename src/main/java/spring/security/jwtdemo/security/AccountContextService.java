package spring.security.jwtdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.AccountRepository;

import javax.persistence.EntityNotFoundException;

/*
Layer responsible for accessing AccountRepository.
 */

@Component
public class AccountContextService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Account account = findByUserId(userId);
        return convertToAccountContext(account);
    }

    private Account findByUserId(String userId) {
        return accountRepository.findByUserId(userId).orElseThrow(() -> new EntityNotFoundException("아이디에 맞는 계정이 없습니다."));
    }

    private AccountContext convertToAccountContext(Account account) {
        return AccountContext.fromAccountModel(account);
    }
}
