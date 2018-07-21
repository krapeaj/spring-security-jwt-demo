package spring.security.jwtdemo.domain;

import javax.persistence.*;

@Entity
public class Account {

    @Id @GeneratedValue
    private long id;

    private String userId;

    private String password;

    private String name;

    private long socialId;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    public long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getSocialId() {
        return socialId;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
