package spring.security.jwtdemo.security;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.security.jwtdemo.domain.Account;
import spring.security.jwtdemo.domain.UserRole;

public class JwtUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtilsTest.class);
    private static final String TEST_USER_ID = "testuserid";
    private static final String TEST_PASSWORD = "testpassword";
    private AccountContext accountContext;

    @Before
    public void setUp() throws Exception {
        Account account = new Account(TEST_USER_ID, TEST_PASSWORD, UserRole.ADMIN);
        accountContext = AccountContext.fromAccountModel(account);
    }

    @Test
    public void generateToken() {
        String token = JwtUtils.generateToken(accountContext);
        logger.error("JWT TOKEN: {}", token);
    }
}