package com.twiter.security.repository

import com.twiter.security.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);
}