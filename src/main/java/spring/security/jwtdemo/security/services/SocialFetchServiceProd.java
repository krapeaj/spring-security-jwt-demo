package spring.security.jwtdemo.security.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.security.jwtdemo.domain.SocialProvider;
import spring.security.jwtdemo.dto.SocialLoginDto;
import spring.security.jwtdemo.social.SocialUserProperty;

@Service
public class SocialFetchServiceProd implements SocialFetchService {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTH_HEADER_PREFIX = "Bearer ";

    @Override
    public SocialUserProperty getSocialUserInfo(SocialLoginDto dto) {
        SocialProvider provider = dto.getSocialProvider();
        RestTemplate template = new RestTemplate();
        return template.exchange(provider.getEndPoint(), HttpMethod.GET, createRequest(dto), provider.getSocialUserProperty()).getBody();
    }

    private HttpEntity<Void> createRequest(SocialLoginDto dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION_HEADER, AUTH_HEADER_PREFIX + dto.getToken());
        return new HttpEntity<>(headers);
    }
}
