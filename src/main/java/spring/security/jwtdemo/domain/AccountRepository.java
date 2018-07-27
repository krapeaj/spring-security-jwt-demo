package spring.security.jwtdemo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);

    Optional<Account> findBySocialIdAndUserId(Long id, String userId);
}
