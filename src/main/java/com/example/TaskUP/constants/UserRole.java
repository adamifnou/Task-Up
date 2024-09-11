package com.example.TaskUP.constants;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum UserRole {
    ADMIN,
    USER;

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.name()));
    }
}