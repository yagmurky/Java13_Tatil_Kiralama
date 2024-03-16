package com.yagmur.repository;

import com.yagmur.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HotelRepository extends MongoRepository<Hotel, String> {

    List<Hotel> findAllByOrderByPointDesc();
}
