package spring.security.jwtdemo.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;

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

    public Collection<UserRole> toList() {
        return Arrays.asList(this);
    }

    public static UserRole getUserRole(String userRole) {
        return Arrays.asList(values()).stream()
                .filter(r -> r.roleName.equals(userRole))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No match of user role was found."));
    }
}
