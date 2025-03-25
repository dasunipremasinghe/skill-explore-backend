package lk.sliit.skillexplorer.api.learningplan.service;

import lk.sliit.skillexplorer.api.learningplan.model.LearningPlan;
import lk.sliit.skillexplorer.api.learningplan.repository.LearningPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LearningPlanService {

    @Autowired
    private LearningPlanRepository repository;

    public LearningPlan createPlan(LearningPlan plan) {
        return repository.save(plan);
    }

    public Optional<LearningPlan> getPlanById(String id) {
        return repository.findById(id);
    }

    public List<LearningPlan> getPlansByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public LearningPlan updatePlan(String id, LearningPlan updatedPlan) {
        return repository.findById(id).map(plan -> {
            plan.setTitle(updatedPlan.getTitle());
            plan.setDescription(updatedPlan.getDescription());
            plan.setTopics(updatedPlan.getTopics());
            plan.setResources(updatedPlan.getResources());
            return repository.save(plan);
        }).orElse(null);
    }

    public boolean deletePlan(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public LearningPlan archivePlan(String id) {
        return repository.findById(id).map(plan -> {
            plan.setArchived(true);
            return repository.save(plan);
        }).orElse(null);
    }
}
