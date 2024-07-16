package com.projects.personal.forum_hub.models;

import com.projects.personal.forum_hub.dto.user.DTOUser;
import jakarta.persistence.*;
import lombok.*;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
// implements UserDetails
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    @Column(unique = true)
    private String email;
    @Setter
    private String username;
    @Setter
    private String password;
    @Setter
    private String profile;

    public User(DTOUser user){
        this.email=user.email();
        this.username=user.name();
        this.password=user.password();
        this.profile=user.profile();
    }

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("USER"));
    }*/

    public String getPassword(){
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }
}
