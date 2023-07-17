package com.twiter.api

import com.twiter.data.entity.Post
import com.twiter.data.entity.User
import com.twiter.data.exception.ApplicationException
import com.twiter.data.exception.ErrorCode
import com.twiter.data.services.PostService
import com.twiter.data.services.UserService
import jakarta.websocket.server.PathParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("like")
class TwitterLikeApi {
    @Autowired
    PostService postService
    @Autowired
    UserService userService

    @PostMapping("{userId}/{postId}")
    ResponseEntity<String> likePost(@PathParam('userId') String userId, @PathParam('postId') String postId) {
        Post post = postService.findById(postId)
        User user = userService.findById(userId)
        if (post == null) {
            throw new ApplicationException(ErrorCode.POST_NOT_FOUND_EXCEPTION)
        } else if (post.getUserLikeIds().contains(userId) || user.getLikedPosts().contains(postId)) {
            throw new ApplicationException(ErrorCode.POST_HAS_ALREADY_BEEN_LIKED)
        }
        user.addLikedPosts(post.getId())
        post.getUserLikeIds().add(userId)
        postService.savePost(post)
        userService.saveUser(user)
        return ResponseEntity.ok("post ${postId} was liked by user ${userId}" as String)
    }

    @DeleteMapping("{userId}/{postId}")
    ResponseEntity<String> deleteLike(@PathParam('userId') String userId, @PathParam('postId') String postId) {
        Post post = postService.findById(postId)
        if (post == null) {
            throw new ApplicationException(ErrorCode.POST_NOT_FOUND_EXCEPTION)
        } else if (!post.getUserLikeIds().contains(userId)) {
            throw new ApplicationException(ErrorCode.POST_HAS_ALREADY_BEEN_LIKED)
        }
        post.getUserLikeIds().remove(userId)
        postService.savePost(post)

        return ResponseEntity.ok("post ${postId} was unliked by user ${userId}" as String)
    }
}
