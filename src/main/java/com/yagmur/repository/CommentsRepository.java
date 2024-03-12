package com.yagmur.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentsRepository extends MongoRepository<CommentsRepository, String> {
}
