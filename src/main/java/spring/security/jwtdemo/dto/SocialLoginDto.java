package spring.security.jwtdemo.dto;

import spring.security.jwtdemo.domain.SocialProvider;

public class SocialLoginDto {
    private SocialProvider socialProvider;
    private String token;

    public SocialLoginDto(SocialProvider provider, String token) {
        this.socialProvider = provider;
        this.token = token;
    }

    public SocialProvider getSocialProvider() {
        return socialProvider;
    }

    public String getToken() {
        return token;
    }
}
