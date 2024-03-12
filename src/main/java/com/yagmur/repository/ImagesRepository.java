package com.yagmur.repository;

import com.yagmur.entity.Images;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<Images, String> {
}
