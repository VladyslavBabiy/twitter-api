package com.twiter

import com.twiter.data.dto.UserInfo
import com.twiter.data.entity.Comment
import com.twiter.data.entity.Post
import com.twiter.data.entity.User
import com.twiter.data.services.CommentService
import com.twiter.data.services.PostService
import com.twiter.data.services.UserInfoService
import com.twiter.data.services.UserService
import org.spockframework.spring.SpringBean
import spock.lang.Shared
import spock.lang.Specification

class UserInfoServiceSpec extends Specification {

    @Shared
    @SpringBean
    PostService postService

    @Shared
    @SpringBean
    CommentService commentService

    @Shared
    @SpringBean
    UserService userService

    def 'Test getUserInfo'() {
        given:
        User user = new User(id: "1", username: "user1")
        user.setLikedPosts(["2", "3"])
        postService.metaClass.getAllPostsByUserId = { String userId -> [new Post(id: "1", userId: userId, content: "Post 1")] }
        commentService.metaClass.getCommentsByUserId = { String userId -> [new Comment(id: "1", userId: userId, content: "Comment 1")] }
        postService.metaClass.getAllPostsByPostIds = { Set<String> postIds -> [new Post(id: "2", userId: "user2", content: "Post 2")] }
        userService.metaClass.findById = { String userId -> user }

        UserInfoService userInfoService = new UserInfoService(postService: postService, commentService: commentService, userService: userService)

        when:
        UserInfo result = userInfoService.getUserInfo("1")

        then:
        result.id == "1"
        result.posts.size() == 1
        result.posts[0].id == "1"
        result.comments.size() == 1
        result.comments[0].id == "1"
        result.likedPosts.size() == 1
        result.likedPosts[0].id == "2"
    }
}
