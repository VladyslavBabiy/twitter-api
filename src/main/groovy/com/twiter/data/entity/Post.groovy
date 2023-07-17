package com.twiter.data.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "posts")
class Post {
    @Id
    ObjectId id
    String userId
    String content
    Set<String> userLikeIds

    ObjectId getId() {
        return id
    }

    def setId(ObjectId id) {
        this.id = id
    }

    String getUserId() {
        return userId
    }

    def setUserId(String userId) {
        this.userId = userId
    }

    String getContent() {
        return content
    }

    def setContent(String content) {
        this.content = content
    }
}
