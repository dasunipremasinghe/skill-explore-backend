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

    public LearningPlan updatePlan(String id, LearningPlan updatedPlan, String requesterEmail) {
        return repository.findById(id).map(existing -> {
            if (!existing.getUserId().equals(requesterEmail)) {
                throw new SecurityException("Unauthorized: You are not the owner of this plan.");
            }
            existing.setTitle(updatedPlan.getTitle());
            existing.setDescription(updatedPlan.getDescription());
            existing.setTopics(updatedPlan.getTopics());
            existing.setArchived(updatedPlan.isArchived());
            return repository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("Plan not found"));
    }

    /*
    public boolean deletePlan(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }*/

    public void deletePlan(String id, String requesterEmail) {
        repository.findById(id).ifPresentOrElse(plan -> {
            if (!plan.getUserId().equals(requesterEmail)) {
                throw new SecurityException("Unauthorized: You cannot delete this plan.");
            }
            repository.deleteById(id);
        }, () -> {
            throw new IllegalArgumentException("Plan not found");
        });
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
