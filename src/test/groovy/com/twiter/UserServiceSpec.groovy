package com.twiter

import com.twiter.data.entity.User
import com.twiter.data.repository.UserRepository
import com.twiter.data.services.UserService
import org.spockframework.spring.SpringBean
import spock.lang.Specification
import spock.lang.AutoCleanup
import spock.lang.Shared

class UserServiceSpec extends Specification {
    @AutoCleanup
    @Shared
    @SpringBean
    UserRepository userRepository

   
    def 'Test findUserByUsername'() {
        given:
        User user1 = new User(id: "1", username: "user1")
        User user2 = new User(id: "2", username: "user2")
        userRepository.saveAll([user1, user2])

        UserService userService = new UserService(userRepository: userRepository)

        when:
        User result = userService.findUserByUsername("user1")

        then:
        result == user1
    }

   
    def 'Test findById'() {
        given:
        User user1 = new User(id: "1", username: "user1")
        User user2 = new User(id: "2", username: "user2")
        userRepository.saveAll([user1, user2])

        UserService userService = new UserService(userRepository: userRepository)

        when:
        User result = userService.findById("1")

        then:
        result == user1
    }

   
    def 'Test subscribe'() {
        given:
        User user1 = new User(id: "1", username: "user1")
        User user2 = new User(id: "2", username: "user2")
        user1.setFollowers([])
        user2.setSubscription([])
        userRepository.saveAll([user1, user2])

        UserService userService = new UserService(userRepository: userRepository)

        when:
        userService.subscribe(user1, user2)

        then:
        User savedUser1 = userRepository.findUserById("1")
        User savedUser2 = userRepository.findUserById("2")

        savedUser1.getFollowers().size() == 1
        savedUser1.getFollowers().contains("2")

        savedUser2.getSubscription().size() == 1
        savedUser2.getSubscription().contains("1")
    }

   
    def 'Test unsubscribe'() {
        given:
        User user1 = new User(id: "1", username: "user1")
        User user2 = new User(id: "2", username: "user2")
        user1.setFollowers(["2"])
        user2.setSubscription(["1"])
        userRepository.saveAll([user1, user2])

        UserService userService = new UserService(userRepository: userRepository)

        when:
        userService.unsubscribe(user1, user2)

        then:
        User savedUser1 = userRepository.findUserById("1")
        User savedUser2 = userRepository.findUserById("2")

        savedUser1.getFollowers().size() == 0
        !savedUser1.getFollowers().contains("2")

        savedUser2.getSubscription().size() == 0
        !savedUser2.getSubscription().contains("1")
    }
}

