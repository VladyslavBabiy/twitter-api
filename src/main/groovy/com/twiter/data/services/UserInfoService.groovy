package com.twiter.data.services

import com.twiter.data.dto.UserInfo
import com.twiter.data.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.stream.Collectors

@Service
class UserInfoService {
    @Autowired
    PostService postService
    @Autowired
    CommentService commentService
    @Autowired
    UserService userService
    UserInfo getUserInfo(String userId) {
        def userInfo = new UserInfo()
        User user = userService.findById(userId)
        userInfo.setPosts( postService.getAllPostsByUserId(userId))
        userInfo.setComments( commentService.getCommentsByUserId(userId))
        Set<String> likedPostsIds = user.getLikedPosts().stream().map { it.toString() }.collect(Collectors.toSet())
        userInfo.setLikedPosts( postService.getAllPostsByPostIds(likedPostsIds))
        userInfo.setId(userId)
        return userInfo
    }
}
