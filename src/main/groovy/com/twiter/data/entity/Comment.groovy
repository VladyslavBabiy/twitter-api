package com.twiter.data.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document
class Comment {
    private String userId
    private String postId
    private String comment

    Comment(String userId, String postId, String comment) {
        this.userId = userId
        this.postId = postId
        this.comment = comment
    }

    String getUserId() {
        return userId
    }

    void setUserId(String userId) {
        this.userId = userId
    }

    String getPostId() {
        return postId
    }

    void setPostId(String postId) {
        this.postId = postId
    }

    String getComment() {
        return comment
    }

    void setComment(String comment) {
        this.comment = comment
    }
}
