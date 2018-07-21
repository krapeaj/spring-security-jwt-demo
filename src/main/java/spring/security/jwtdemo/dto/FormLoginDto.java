package spring.security.jwtdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FormLoginDto {

    @JsonProperty(value = "userId")
    private String userId;

    @JsonProperty(value = "password")
    private String password;

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
