package spring.security.jwtdemo.security.services;

import spring.security.jwtdemo.dto.SocialLoginDto;
import spring.security.jwtdemo.social.SocialUserProperty;

public interface SocialFetchService {

    SocialUserProperty getSocialUserInfo(SocialLoginDto dto);

}
