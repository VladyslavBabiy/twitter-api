package com.twiter

import com.twiter.data.entity.Comment
import com.twiter.data.repository.CommentRepository
import com.twiter.data.services.CommentService
import org.spockframework.spring.SpringBean
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

class CommentServiceSpec extends Specification {
    @AutoCleanup
    @Shared
    @SpringBean
    CommentRepository commentRepository

    def 'Test getCommentsByUserId'() {
        given:
        Comment comment1 = new Comment(id: "1", userId: "user1", postId: "post1", content: "Comment 1")
        Comment comment2 = new Comment(id: "2", userId: "user1", postId: "post2", content: "Comment 2")
        Comment comment3 = new Comment(id: "3", userId: "user2", postId: "post1", content: "Comment 3")
        commentRepository.saveAll([comment1, comment2, comment3])

        CommentService commentService = new CommentService(commentRepository: commentRepository)

        when:
        List<Comment> result = commentService.getCommentsByUserId("user1")

        then:
        result.size() == 2
        result.contains(comment1)
        result.contains(comment2)
        !result.contains(comment3)
    }

    def 'Test getCoommentsByPostId'() {
        given:
        Comment comment1 = new Comment(id: "1", userId: "user1", postId: "post1", content: "Comment 1")
        Comment comment2 = new Comment(id: "2", userId: "user2", postId: "post1", content: "Comment 2")
        Comment comment3 = new Comment(id: "3", userId: "user3", postId: "post2", content: "Comment 3")
        commentRepository.saveAll([comment1, comment2, comment3])

        CommentService commentService = new CommentService(commentRepository: commentRepository)

        when:
        List<Comment> result = commentService.getCoommentsByPostId("post1")

        then:
        result.size() == 2
        result.contains(comment1)
        result.contains(comment2)
        !result.contains(comment3)
    }
}
