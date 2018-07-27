package spring.security.jwtdemo.security.tokens;

import spring.security.jwtdemo.dto.SocialLoginDto;

public class SocialPreAuthorizationToken extends PreAuthorizationToken {

    public SocialPreAuthorizationToken(SocialLoginDto dto) {
        super(dto);
    }

    public SocialLoginDto getSocialLoginDto() {
        return (SocialLoginDto) super.getCredentials();
    }
}
