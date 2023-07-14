package com.twiter.testcontainers


import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

@Testcontainers
@EnableAutoConfiguration
@EnableMongoRepositories
class MongoDbTestContainer extends Specification {
    static final int MONGO_PORT = 27017

    @Shared
    static GenericContainer mongo = new GenericContainer("mongo:3.2")
            .withExposedPorts(MONGO_PORT)

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry registry) {
        mongo.start()
        registry.add("spring.data.mongodb.uri", () -> mongo.replicaSetUrl)
    }
}
