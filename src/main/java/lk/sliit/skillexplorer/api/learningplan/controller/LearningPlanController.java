package lk.sliit.skillexplorer.api.learningplan.controller;

import lk.sliit.skillexplorer.api.learningplan.model.LearningPlan;
import lk.sliit.skillexplorer.api.learningplan.repository.LearningPlanRepository;
import lk.sliit.skillexplorer.api.learningplan.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/learning-plans")
public class LearningPlanController {

    @Autowired
    private LearningPlanService service;

    @Autowired
    private LearningPlanRepository repository;

    @PostMapping
    public ResponseEntity<LearningPlan> createPlan(@RequestBody LearningPlan plan) {
        return ResponseEntity.ok(service.createPlan(plan));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LearningPlan>> getPlansByUser(@PathVariable String userId) {
        return ResponseEntity.ok(service.getPlansByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPlan> getPlanById(@PathVariable String id) {
        return service.getPlanById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LearningPlan> updatePlan(
            @PathVariable String id,
            @RequestBody LearningPlan updatedPlan,
            Authentication authentication) {

        String email = authentication.getName();
        return ResponseEntity.ok(service.updatePlan(id, updatedPlan, email));
    }

    /*
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable String id) {
        boolean deleted = service.deletePlan(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable String id, Authentication authentication) {
        String email = authentication.getName();
        service.deletePlan(id, email);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/archive/{id}")
    public ResponseEntity<LearningPlan> archivePlan(@PathVariable String id) {
        LearningPlan archived = service.archivePlan(id);
        return archived != null ? ResponseEntity.ok(archived) : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<LearningPlan>> searchPlans(@RequestParam("q") String query) {
        return ResponseEntity.ok(service.searchPlans(query));
    }

    @GetMapping("/random")
    public List<LearningPlan> getRandomPlans(@RequestParam String excludeUserId) {
        List<LearningPlan> allPlans = repository.findAll();
        List<LearningPlan> filtered = allPlans.stream()
                .filter(p -> !p.getUserId().equals(excludeUserId))
                .collect(Collectors.toList());
        Collections.shuffle(filtered);
        return filtered.stream().limit(6).collect(Collectors.toList());
    }

}
