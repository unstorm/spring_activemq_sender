package com.example.activemq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.activemq.model.User;

public interface UserRepository extends MongoRepository<User,String> {
    public User findByName(String name);
}
