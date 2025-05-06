package lk.sliit.skillexplorer.api.learningplan.controller;

import lk.sliit.skillexplorer.api.learningplan.model.UserLearningProgress;
import lk.sliit.skillexplorer.api.learningplan.service.UserLearningProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-progress")
public class UserLearningProgressController {

    @Autowired
    private UserLearningProgressService progressService;

    @GetMapping("/{userId}/{planId}")
    public Optional<UserLearningProgress> getProgress(@PathVariable String userId, @PathVariable String planId) {
        return progressService.getProgress(userId, planId);
    }

    @GetMapping("/{userId}")
    public List<UserLearningProgress> getAllProgressByUser(@PathVariable String userId) {
        return progressService.getAllProgressByUser(userId);
    }

    @PostMapping
    public UserLearningProgress saveProgress(@RequestBody UserLearningProgress progress) {
        return progressService.saveProgress(progress);
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable String id) {
        progressService.deleteProgress(id);
    }
}
