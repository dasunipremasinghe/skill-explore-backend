package lk.sliit.skillexplorer.api.learningprogress.service;
import lk.sliit.skillexplorer.api.learningprogress.model.ProgressUpdate;

import lk.sliit.skillexplorer.api.learningprogress.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository repo;

    // Get all progress updates
    public List<ProgressUpdate> getAll() {
        return repo.findAll();
    }

    // Get progress updates for a specific user
    public List<ProgressUpdate> getByUser(String userId) {
        return repo.findByUserId(userId);
    }

    // Create a new progress update
    public ProgressUpdate create(ProgressUpdate update) {
        return repo.save(update);
    }

    // Update an existing progress update
    public ProgressUpdate update(String id, ProgressUpdate update) {
        update.setId(id);
        return repo.save(update);
    }

    // Delete a progress update by ID
    public void delete(String id) {
        repo.deleteById(id);
    }
}
