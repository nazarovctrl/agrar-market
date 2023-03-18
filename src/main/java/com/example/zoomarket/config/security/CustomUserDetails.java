package com.example.zoomarket.config.security;

import com.example.zoomarket.enums.ProfileRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Long id;
    private String username;

    private ProfileRole role;

    public CustomUserDetails(Long id, String username,  ProfileRole role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new LinkedList<>();
        list.add(new SimpleGrantedAuthority(role.name()));
        return list;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // status
        return true;
    }

    public Long getId() {
        return id;
    }

    public ProfileRole getRole() {
        return role;
    }
}
