package com.twiter.data.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.MongoId

@Document('user')
class User {
    private @MongoId
    ObjectId id
    private String username
    private Set<ObjectId> subscription
    private Set<ObjectId> followers
    private Set<ObjectId> likedPosts

    ObjectId getId() {
        return id
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

    Set<ObjectId> getSubscription() {
        return subscription
    }

    void addSubscription(String userId) {
        this.subscription.add(userId)
    }

    void setSubscription(Set<ObjectId> subscription) {
        this.subscription = subscription
    }

    Set<ObjectId> getFollowers() {
        return followers
    }

    void setFollowers(Set<ObjectId> followers) {
        this.followers = followers
    }

    Set<ObjectId> getLikedPosts() {
        return likedPosts
    }

    void addLikedPosts(ObjectId likedPosts) {
        this.likedPosts.add(likedPosts)
    }

}
