package com.example.demo.service;

import com.example.demo.model.ProgressUpdate;
import com.example.demo.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository repo;

    public List<ProgressUpdate> getAll() {
        return repo.findAll();
    }

    public List<ProgressUpdate> getByUser(String userId) {
        return repo.findByUserId(userId);
    }

    public ProgressUpdate create(ProgressUpdate update) {
        return repo.save(update);
    }

    public ProgressUpdate update(String id, ProgressUpdate update) {
        update.setId(id);
        return repo.save(update);
    }

    public void delete(String id) {
        repo.deleteById(id);
    }
}
