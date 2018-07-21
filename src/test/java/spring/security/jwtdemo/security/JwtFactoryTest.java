package spring.security.jwtdemo.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.UserRole;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtFactoryTest {
    private static final Logger logger = LoggerFactory.getLogger(JwtFactoryTest.class);
    private static final String TEST_USER_ID = "testuserid";
    private static final String TEST_PASSWORD = "testpassword";
    private AccountContext accountContext;

    @Autowired
    private JwtFactory jwtFactory;

    @Before
    public void setUp() throws Exception {
        Account account = new Account(TEST_USER_ID, TEST_PASSWORD, UserRole.ADMIN);
        accountContext = AccountContext.fromAccountModel(account);
    }

    @Test
    public void generateToken() {
        String token = jwtFactory.generateToken(accountContext);
        logger.error("JWT TOKEN: {}", token);
    }
}