package com.yagmur.repository;

import com.yagmur.entity.FacilityFeatures;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacilityFeaturesRepository extends MongoRepository<FacilityFeatures, String> {
}
