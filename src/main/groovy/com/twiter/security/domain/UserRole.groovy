package com.twiter.security.domain

import org.springframework.security.core.GrantedAuthority;


class UserRole implements GrantedAuthority {
    UserRole (Role role){
        this.role = role
    }

    private Role role;

    @Override
    String getAuthority() {
        return Role.getName();
    }

    Role getRole() {
        return role
    }

    void setRole(Role role) {
        this.role = role
    }
}
