package spring.security.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenDto {

    @JsonProperty(value = "token")
    private String token;

    public TokenDto(String token) {
        this.token = token;
    }
}
