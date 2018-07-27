package spring.security.jwtdemo.security.services;

import org.junit.Before;
import org.junit.Test;
import spring.security.jwtdemo.domain.SocialProvider;
import spring.security.jwtdemo.dto.SocialLoginDto;
import spring.security.jwtdemo.social.SocialUserProperty;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SocialFetchServiceProdTest {

    private SocialFetchServiceProd socialFetchServiceProd;
    private SocialLoginDto dto;

    @Before
    public void setUp() throws Exception {
        socialFetchServiceProd = new SocialFetchServiceProd();
        dto = new SocialLoginDto(SocialProvider.KAKAO, "");
    }

    @Test
    public void getSocialUserInfo() {
        SocialUserProperty property = socialFetchServiceProd.getSocialUserInfo(dto);
        assertThat(property.getNickname(), is("tinyjae@hotmail.com"));
    }
}