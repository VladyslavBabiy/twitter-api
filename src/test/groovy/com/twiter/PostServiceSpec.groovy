package com.twiter

import com.twiter.data.entity.Post
import com.twiter.data.repository.PostRepository
import com.twiter.data.services.PostService
import org.spockframework.spring.SpringBean
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
class PostServiceSpec extends Specification {

    @Shared
    @SpringBean
    PostRepository postRepository

    def 'Test getAllPostsByPostIds'() {
        given:
        Post post1 = new Post(id: "1", userId: "user1", content: "Post 1")
        Post post2 = new Post(id: "2", userId: "user1", content: "Post 2")
        Post post3 = new Post(id: "3", userId: "user2", content: "Post 3")
        postRepository.saveAll([post1, post2, post3])

        PostService postService = new PostService(postRepository: postRepository)

        when:
        List<Post> result = postService.getAllPostsByPostIds(["1", "2"])

        then:
        result.size() == 2
        result.contains(post1)
        result.contains(post2)
        !result.contains(post3)
    }
}
