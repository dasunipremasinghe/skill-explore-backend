package lk.sliit.skillexplorer.api.learningplan.service;

import lk.sliit.skillexplorer.api.learningplan.model.UserLearningProgress;
import lk.sliit.skillexplorer.api.learningplan.repository.UserLearningProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLearningProgressService {

    @Autowired
    private UserLearningProgressRepository repository;

    public Optional<UserLearningProgress> getProgress(String userId, String planId) {
        return repository.findByUserIdAndLearningPlanId(userId, planId);
    }

    public List<UserLearningProgress> getAllProgressByUser(String userId) {
        return repository.findAllByUserId(userId);
    }

    public UserLearningProgress saveProgress(UserLearningProgress incoming) {
        Optional<UserLearningProgress> existing = repository.findByUserIdAndLearningPlanId(
                incoming.getUserId(), incoming.getLearningPlanId()
        );

        if (existing.isPresent()) {
            UserLearningProgress current = existing.get();
            current.setTopicProgressList(incoming.getTopicProgressList());
            return repository.save(current);
        }

        return repository.save(incoming);
    }

    public void deleteProgress(String id) {
        repository.deleteById(id);
    }
}

