package com.twiter.security.domain


import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document(collection = "users")
class User {
    User(ObjectId objectId ,String username, String password, Set<UserRole> userRoles) {
        this.id = objectId
        this.username = username
        this.password = password
        this.userRoles = userRoles
    }

    private @MongoId ObjectId id
    private String username
    private String password
    private Set<UserRole> userRoles = []

    ObjectId getId() {
        return id
    }

    void setId(ObjectId id) {
        this.id = id
    }

    String getUsername() {
        return username
    }

    void setUsername(String username) {
        this.username = username
    }

    String getPassword() {
        return password
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
