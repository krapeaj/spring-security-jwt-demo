package spring.security.jwtdemo.domain;

import javax.persistence.*;

@Entity
public class Account {

    @Id @GeneratedValue
    private long id;

    private String userId;

    private String password;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserRole authority;

}
