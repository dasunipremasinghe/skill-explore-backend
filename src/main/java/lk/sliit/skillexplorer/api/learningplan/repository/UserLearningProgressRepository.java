package lk.sliit.skillexplorer.api.learningplan.repository;

import lk.sliit.skillexplorer.api.learningplan.model.UserLearningProgress;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserLearningProgressRepository extends MongoRepository<UserLearningProgress, String> {
    Optional<UserLearningProgress> findByUserIdAndLearningPlanId(String userId, String learningPlanId);
    List<UserLearningProgress> findAllByUserId(String userId);
    void deleteByUserIdAndLearningPlanId(String userId, String learningPlanId);
}

