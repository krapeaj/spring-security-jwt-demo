package spring.security.jwtdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtFactory {
    private static final Logger logger = LoggerFactory.getLogger(JwtFactory.class);
    private static final String SIGNING_KEY = "jwttest";

    public String generateToken(AccountContext accountContext) {

        String token = JWT.create()
                .withIssuer("krapeaj")
                .withClaim("USER_ROLE", accountContext.getAuthority())
                .sign(Algorithm.HMAC256(SIGNING_KEY));

        return token;
    }
}
