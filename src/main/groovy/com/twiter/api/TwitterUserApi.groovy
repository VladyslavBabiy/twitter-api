package com.twiter.api

import com.twiter.data.dto.UserInfo
import com.twiter.data.entity.User
import com.twiter.data.exception.ApplicationException
import com.twiter.data.exception.ErrorCode
import com.twiter.data.services.UserInfoService
import com.twiter.data.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class TwitterUserApi {
    @Autowired
    UserService userService
    @Autowired
    UserInfoService userInfoService

    // User registration
    @PostMapping("/create")
    ResponseEntity<User> createUser(@RequestBody User user) {
        // Save user details to the database
        userService.saveUser(user)
        return ResponseEntity.ok(user)
    }

    // User editing
    @PutMapping("/update")
    User editUser(@RequestBody User updatedUser) {
        User user = userService.findByIdById(updatedUser.getId())
        if (!user) {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND_EXCEPTION)
        }
        userService.updateUser(updatedUser)
        return user
    }

    @PostMapping("/{userId}/subscription/{followerId}")
    ResponseEntity<String> subscribe(@PathVariable("userId") String userId, @PathVariable("followerId") String followerId) {
        User user = userService.findById(userId)
        User follower = userService.findById(followerId)
        if (user == null || follower == null) {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND_EXCEPTION)
        }else if (user == follower || follower.getFollowers().contains(user.getId())){
            throw new ApplicationException(ErrorCode.USER_SUBSCRIPTION_EXCEPTION)
        }
        userService.subscribe(user, follower)
        return ResponseEntity.ok("User subscribed")
    }

    @GetMapping("/{userId}/userInfo")
    UserInfo getUserInfo(@PathVariable("userId") String userId) {
        return userInfoService.getUserInfo(userId)
    }

    @DeleteMapping("/{userId}/subscription/{followerId}")
    ResponseEntity<String> unsubscribe(@PathVariable("userId") String userId, @PathVariable("followerId") String followerId) {
        User user = userService.findById(userId)
        User follower = userService.findById(followerId)
        if (user == null || follower == null) {
            throw new ApplicationException(ErrorCode.USER_NOT_FOUND_EXCEPTION)
        }else if (user == follower || !follower.getFollowers().contains(user.getId())){
            throw new ApplicationException(ErrorCode.USER_SUBSCRIPTION_EXCEPTION)
        }
        userService.unsubscribe(user, follower)
        return ResponseEntity.ok("User unsubscribed")
    }

    // User deletion
    @DeleteMapping("/{userId}")
    ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        // Delete user from the database
        userService.deleteUser(userId)
        return ResponseEntity.ok("User  deleted")
    }
}
