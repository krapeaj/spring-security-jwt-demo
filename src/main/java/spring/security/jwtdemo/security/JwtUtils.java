package spring.security.jwtdemo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    private static final String SIGNING_KEY = "jwttest";

    public static String generateToken(AccountContext accountContext) {

        String token = JWT.create()
                .withClaim("USER_ID", accountContext.getUsername())
                .withClaim("USER_ROLE", accountContext.getAuthority())
                .sign(Algorithm.HMAC256(SIGNING_KEY));
        logger.error("Token Issued: user id: {}, user role: {}", accountContext.getUsername(), accountContext.getAuthority());

        return token;
    }


    public static AccountContext decodeJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .build()
                    .verify(token);
            logger.error(decodedJWT.getPayload());
            String userId = decodedJWT.getClaim("USER_ID").asString();
            String userRole = decodedJWT.getClaim("USER_ROLE").asString();
            logger.error("USERID: {}", userId);
            logger.error("USERROLE: {}", userRole);
            return AccountContext.fromDecodedJwtPayload(userId, userRole);
        } catch (Exception e) {
            logger.error("Error occurred while decoding JWT: {}", e.getMessage());
            throw new AuthenticationFailureException(e.getMessage());
        }
    }


}
