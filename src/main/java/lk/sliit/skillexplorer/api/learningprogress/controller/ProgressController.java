package lk.sliit.skillexplorer.api.learningprogress.controller;

import com.example.demo.model.ProgressUpdate;
import com.example.demo.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173"})
public class ProgressController {

    @Autowired
    private ProgressService service;

    @GetMapping
    public List<ProgressUpdate> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<ProgressUpdate> getByUser(@PathVariable String userId) {
        return service.getByUser(userId);
    }

    @PostMapping
    public ProgressUpdate create(@RequestBody ProgressUpdate update) {
        return service.create(update);
    }

    @PutMapping("/{id}")
    public ProgressUpdate update(@PathVariable String id, @RequestBody ProgressUpdate update) {
        return service.update(id, update);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
