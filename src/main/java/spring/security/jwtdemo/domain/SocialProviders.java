package spring.security.jwtdemo.domain;

import spring.security.jwtdemo.social.KakaoUserProperty;
import spring.security.jwtdemo.social.SocialUserProperty;

public enum SocialProviders {
    KAKAO("https://kapi.kakao.com/v2/user/me", KakaoUserProperty.class);

    private String endPoint;
    private Class<? extends SocialUserProperty> socialUserProperty;

    SocialProviders(String endPoint, Class<? extends SocialUserProperty> socialUserProperty) {
        this.endPoint = endPoint;
        this.socialUserProperty = socialUserProperty;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public Class<? extends SocialUserProperty> getSocialUserProperty() {
        return socialUserProperty;
    }
}
