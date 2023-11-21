package com.users.testusers.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;


@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    String username;
    @Column(nullable = false)
    String password;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "number")
    private String number;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "countrycode")
    private String countrycode;
    @Column(name = "created")
    @DateTimeFormat
    @CreationTimestamp
    private LocalDate created;
    @Column(name = "modified")
    @DateTimeFormat
    @UpdateTimestamp
    private Date modified;
    @Column(name = "last_login")
    @DateTimeFormat
    @UpdateTimestamp
    private LocalDateTime lastlogin;
    @Column(name = "is_active")
    private boolean active = true;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Column(name="roles")
    private Set<Role> roles;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }

}
