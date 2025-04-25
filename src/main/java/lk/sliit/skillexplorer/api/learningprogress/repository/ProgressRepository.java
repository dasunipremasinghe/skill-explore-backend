package com.example.demo.repository;

import com.example.demo.model.ProgressUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProgressRepository extends MongoRepository<ProgressUpdate, String> {
    List<ProgressUpdate> findByUserId(String userId);
}
