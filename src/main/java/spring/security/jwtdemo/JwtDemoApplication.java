package spring.security.jwtdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.AccountRepository;
import spring.security.jwtdemo.domain.UserRole;

@SpringBootApplication
public class JwtDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner bootstrapTestAccount(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Account account = new Account("krapeaj", passwordEncoder.encode("password"), UserRole.ADMIN);
            accountRepository.save(account);
        };
    }
}
