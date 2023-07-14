package com.twiter.repository

import com.twiter.security.domain.UserRole
import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId
import org.springframework.security.core.userdetails.UserDetails

@Document(collection = "users")
class UserDto implements UserDetails {
    private @MongoId
    ObjectId id
    private String username
    private String password
    private Set<UserRole> userRoles = []

    @Override
    String getPassword() {
        return password
    }

    @Override
    Set<UserRole> getAuthorities() {
        return this.userRoles
    }

    @Override
    String getUsername() {
        return this.username
    }


    @Override
    boolean isAccountNonExpired() {
        return false
    }

    @Override
    boolean isAccountNonLocked() {
        return false
    }

    @Override
    boolean isCredentialsNonExpired() {
        return false
    }

    @Override
    boolean isEnabled() {
        return false
    }

    @Override
    boolean equals(Object o) {
        if (this == o)
            return true
        if (o == null || getClass() != o.getClass())
            return false
        UserDto user = (UserDto) o
        return Objects.equals(username, user.username)
    }

    @Override
    int hashCode() {
        return Objects.hash(username)
    }

    ObjectId getId() {
        return id
    }

    void setId(ObjectId id) {
        this.id = id
    }

    void setUsername(String username) {
        this.username = username
    }

    void setPassword(String password) {
        this.password = password
    }

    Set<UserRole> getUserRoles() {
        return userRoles
    }

    void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles
    }
}
