package com.twiter.data.repository

import com.twiter.data.entity.Comment
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query;

interface CommentRepository extends MongoRepository<Comment, String>{
    @Query("{postId: ?0}")
    List<Comment> findAllByPostId(String postId);
    @Query("{userId: ?0}")
    List<Comment> findAllByUserId(String userId);
}
