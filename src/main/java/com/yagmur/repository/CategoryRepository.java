package com.yagmur.repository;

import com.yagmur.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
