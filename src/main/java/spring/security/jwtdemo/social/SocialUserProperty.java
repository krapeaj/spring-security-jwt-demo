package spring.security.jwtdemo.social;

/*
Kind of a DTO class to take user info from the authentication provider (server) after the application server
sends a request with the client's (user) access token (provided by the authentication provider).
 */

public interface SocialUserProperty {

    String getNickname();

    String getProfileHref();

    Long getSocialUniqueId();
}
