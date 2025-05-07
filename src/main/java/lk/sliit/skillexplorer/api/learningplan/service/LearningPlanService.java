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
        plan.setArchived(false); // Default to not archived when creating
        return repository.save(plan);
    }

    public List<LearningPlan> getPlansByUser(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<LearningPlan> getPlanById(String id) {
        return repository.findById(id);
    }

    public LearningPlan updatePlan(String id, LearningPlan updatedPlan) {
        return repository.findById(id).map(existingPlan -> {
            existingPlan.setTitle(updatedPlan.getTitle());
            existingPlan.setDescription(updatedPlan.getDescription());
            existingPlan.setTopics(updatedPlan.getTopics());
            existingPlan.setUserId(updatedPlan.getUserId());
            existingPlan.setArchived(updatedPlan.isArchived());
            return repository.save(existingPlan);
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
            plan.setArchived(!plan.isArchived()); // Toggle archived status
            return repository.save(plan);
        }).orElse(null);
    }

    public List<LearningPlan> searchPlans(String query) {
        return repository.findAll().stream()
                .filter(plan ->
                        plan.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                                plan.getDescription().toLowerCase().contains(query.toLowerCase()) ||
                                plan.getTopics().stream()
                                        .anyMatch(topic -> topic.getTitle().toLowerCase().contains(query.toLowerCase()))
                )
                .toList();
    }
}
