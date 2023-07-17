package com.twiter.data.services

import com.twiter.data.entity.Comment
import com.twiter.data.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CommentService {
    @Autowired
    CommentRepository commentRepository

    def saveComment(Comment comment) {
       commentRepository.save(comment)
    }

    List<Comment> getCoommentsByPostId(String postId) {
        return commentRepository.findAllByPostId(postId)
    }
    List<Comment> getCommentsByUserId(String userId) {
        return commentRepository.findAllByUserId(userId)
    }
}
