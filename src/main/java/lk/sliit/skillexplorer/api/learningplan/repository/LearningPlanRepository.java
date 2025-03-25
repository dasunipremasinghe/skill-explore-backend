package lk.sliit.skillexplorer.api.learningplan.repository;

import lk.sliit.skillexplorer.api.learningplan.model.LearningPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LearningPlanRepository extends MongoRepository<LearningPlan, String> {
    List<LearningPlan> findByUserId(String userId);
}
