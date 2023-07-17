package com.twiter.data.repository

import com.twiter.data.entity.Comment
import com.twiter.data.entity.Post
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface PostRepository extends MongoRepository<Post, String> {
    @Query("{userId: ?0}")
    List<Post> findAllByUserId(String userId)
    @Query("{_id: {in: ?0}}")
    List<Post> findAllByPostIds(Set<String> postIds)
}
