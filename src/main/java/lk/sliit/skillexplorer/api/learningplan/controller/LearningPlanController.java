package lk.sliit.skillexplorer.api.learningplan.controller;

import lk.sliit.skillexplorer.api.learningplan.model.LearningPlan;
import lk.sliit.skillexplorer.api.learningplan.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/learning-plans")
public class LearningPlanController {

    @Autowired
    private LearningPlanService service;

    @PostMapping
    public ResponseEntity<LearningPlan> createPlan(@RequestBody LearningPlan plan) {
        return ResponseEntity.ok(service.createPlan(plan));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPlan> getPlanById(@PathVariable String id) {
        return service.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPlan>> getPlansByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getPlansByUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPlan> updatePlan(@PathVariable String id, @RequestBody LearningPlan updatedPlan) {
        LearningPlan updated = service.updatePlan(id, updatedPlan);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable String id) {
        boolean deleted = service.deletePlan(id);
        return deleted
                ? ResponseEntity.ok().body(Map.of("message", "Deleted successfully"))
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/archive/{id}")
    public ResponseEntity<LearningPlan> archivePlan(@PathVariable String id) {
        LearningPlan archived = service.archivePlan(id);
        return archived != null ? ResponseEntity.ok(archived) : ResponseEntity.notFound().build();
    }
}
