package com.twiter.api

import com.twiter.data.entity.Post
import com.twiter.data.exception.ApplicationException
import com.twiter.data.exception.ErrorCode
import com.twiter.data.services.PostService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/post")
class TwitterPostApi {
    @Autowired
    PostService postService

    // Create a post
    @PostMapping("/posts")
    Post createPost(@RequestBody Post post) {
        postService.savePost(post)
        return post
    }

    // Edit a post
    @PutMapping("/posts/{postId}")
    Post editPost(@PathVariable("postId") String postId, @RequestBody Post updatedPost) {
        // Find the existing post
        Post post = postService.findById(postId)
        if (!post) {
            throw new ApplicationException(ErrorCode.POST_NOT_FOUND_EXCEPTION)
        }

        // Update the post details
        post.setContent(updatedPost.getContent())

        // Save the updated post to the database
        postService.savePost(post)
        return post
    }

    @GetMapping("/posts/{userId}")
    List<Post> getAllPostByUserId(@PathVariable("userId") String userId) {
        return postService.getAllPostsByUserId(userId)
    }

    // Delete a post
    @DeleteMapping("/posts/{postId}")
    void deletePost(@PathVariable("postId") String postId) {
        // Delete post from the database
        postService.deletePost(postId)
    }
}
