package com.twiter.data.services

import com.twiter.data.entity.User
import com.twiter.data.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {
    @Autowired
    UserRepository userRepository

    User findUserByUsername(String username) {
        userRepository.findUserByUsername(username)
    }
    def saveUser(User user) {
        userRepository.save(user)
    }

    def updateUser(User user) {
        userRepository.save(user)
    }

    def deleteUser(String s) {
        userRepository.deleteById(s);
    }

    void subscribe(User user, User follower) {
        follower.getSubscription().add(user.getId())
        user.getFollowers().add(follower.getId())
        userRepository.save(user)
        userRepository.save(follower)
    }

    User findById(String objectId) {
        return userRepository.findUserById(objectId)
    }

    void unsubscribe(User user, User follower) {
        follower.getSubscription().remove(user.getId())
        user.getFollowers().remove(follower.getId())
        userRepository.save(user)
        userRepository.save(follower)
    }
}
