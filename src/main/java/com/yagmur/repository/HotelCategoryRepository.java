package com.yagmur.repository;

import com.yagmur.entity.HotelCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelCategoryRepository extends MongoRepository<HotelCategory, String> {
}
