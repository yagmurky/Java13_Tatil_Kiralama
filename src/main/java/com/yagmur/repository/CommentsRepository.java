package com.yagmur.repository;

import com.yagmur.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, String> {
    List<Comments> findByHotelId(String hotelId);
}
