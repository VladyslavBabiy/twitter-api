package com.twiter.data.repository


import com.twiter.data.entity.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository extends MongoRepository<User, String> {

    @Query("{username:'?0'}")
    User findUserByUsername(String username)

    @Query("{_id:?0}")
    User findUserById(String id);

}
