package com.yagmur.repository;

import com.yagmur.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserProfile, String> {
    Optional<UserProfile> findByAuthId(String authId);
}
