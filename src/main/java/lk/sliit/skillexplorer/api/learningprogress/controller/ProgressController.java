package lk.sliit.skillexplorer.api.learningprogress.controller;

import lk.sliit.skillexplorer.api.learningprogress.model.ProgressUpdate;

import lk.sliit.skillexplorer.api.learningprogress.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ProgressController {

    @Autowired
    private ProgressService service;

    // Get all progress updates
    @GetMapping
    public List<ProgressUpdate> getAll() {
        return service.getAll();
    }

    // Get progress updates for a specific user
    @GetMapping("/user/{userId}")
    public List<ProgressUpdate> getByUser(@PathVariable String userId) {
        return service.getByUser(userId);
    }

    // Create a new progress update
    @PostMapping
    public ProgressUpdate create(@RequestBody ProgressUpdate update) {
        return service.create(update);
    }

    // Update an existing progress update
    @PutMapping("/{id}")
    public ProgressUpdate update(@PathVariable String id, @RequestBody ProgressUpdate update) {
        return service.update(id, update);
    }

    // Delete a progress update by ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
