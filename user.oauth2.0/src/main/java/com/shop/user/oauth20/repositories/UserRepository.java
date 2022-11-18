package com.shop.user.oauth20.repositories;


import com.shop.core.model.entity.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{'account.username': ?0}")
    List<User> findByUsername(String username);
}
