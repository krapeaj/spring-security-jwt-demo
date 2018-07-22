package spring.security.jwtdemo.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
