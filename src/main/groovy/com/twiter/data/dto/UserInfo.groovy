package com.twiter.data.dto

import com.twiter.data.entity.Comment
import com.twiter.data.entity.Post

class UserInfo {
    private String id
    private List<Post> posts
    private List<Comment> comments
    private List<Post> likedPosts

    String getId() {
        return id
    }

    void setId(String id) {
        this.id = id
    }

    List<Post> getPosts() {
        return posts
    }

    void setPosts(List<Post> posts) {
        this.posts = posts
    }

    List<Comment> getComments() {
        return comments
    }

    void setComments(List<Comment> comments) {
        this.comments = comments
    }

    List<Post> getLikedPosts() {
        return likedPosts
    }

    void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts
    }
}
