package spring.security.jwtdemo.social;

public class KakaoUserProperty implements SocialUserProperty {

    private String userNickname;
    private String profileHref;
    private Long socialUniqueId;

    @Override
    public String getNickname() {
        return null;
    }

    @Override
    public String getProfileHref() {
        return null;
    }

    @Override
    public Long getSocialUniqueId() {
        return socialUniqueId;
    }
}
