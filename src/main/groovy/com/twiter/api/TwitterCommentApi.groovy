package com.twiter.api

import com.twiter.data.entity.Comment
import com.twiter.data.services.CommentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/comment")
class TwitterCommentApi {
    @Autowired
    CommentService commentService

    @PostMapping
    ResponseEntity<String> createComment(@RequestBody Comment commentDto) {
        commentService.saveComment(commentDto)
        return ResponseEntity.ok("comment was created" as String)
    }

    @GetMapping('/{postId}')
    List<Comment> getCommentsByPostId(@PathVariable("postId") String postId) {
        return commentService.getCoommentsByPostId(postId)
    }
}
