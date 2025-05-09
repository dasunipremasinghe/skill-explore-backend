package lk.sliit.skillexplorer.api.learningprogress.repository;

import lk.sliit.skillexplorer.api.learningprogress.model.ProgressUpdate;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProgressRepository extends MongoRepository<ProgressUpdate, String> {
    List<ProgressUpdate> findByUserId(String userId);
}
