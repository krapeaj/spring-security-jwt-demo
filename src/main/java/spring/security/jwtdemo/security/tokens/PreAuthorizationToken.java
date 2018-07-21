package spring.security.jwtdemo.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import spring.security.jwtdemo.dto.FormLoginDto;

/*
This is a Pre- authorization token object, which is created after going through the filter but is
not yet authorized by a provider.
 */

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String userId, String password) {
        super(userId, password);
    }

    public PreAuthorizationToken(FormLoginDto dto) {
        this(dto.getUserId(), dto.getPassword());
    }

    public String getUserId() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
