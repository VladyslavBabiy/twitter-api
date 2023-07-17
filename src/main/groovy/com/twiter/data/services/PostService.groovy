package com.twiter.data.services

import com.twiter.data.entity.Post
import com.twiter.data.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostService {
    @Autowired
    PostRepository postRepository

    def savePost(Post post) {
        postRepository.save(post)
    }

    Post findById(String s) {
        return postRepository.findById(s) as Post
    }

    def deletePost(String id) {
        postRepository.deleteById(id);
    }

    List<Post> getAllPostsByUserId(String userId) {
        return postRepository.findAllByUserId(userId)
    }

    List<Post> getAllPostsByPostIds(Set<String> postIds) {
        return postRepository.findAllByPostIds(postIds)
    }
}
