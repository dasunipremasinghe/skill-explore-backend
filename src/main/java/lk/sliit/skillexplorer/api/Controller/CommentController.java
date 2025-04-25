package lk.sliit.skillexplorer.api.Controller;


import lk.sliit.skillexplorer.api.Entity.Comment;
import lk.sliit.skillexplorer.api.Entity.CommentUpdatePayload;
import lk.sliit.skillexplorer.api.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/Comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public Collection<Comment> getComments() {
        return commentService.getComments();
    }

    @PostMapping("/AddComment")
    public Comment postComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    @GetMapping("/{id}")
    public Optional<Comment> getByCommentId(@PathVariable("id") String id) {
        return commentService.getCommentById(id);
    }

    @DeleteMapping("/DeleteComment/{id}")
    public Optional<Comment> deleteByCommentId(@PathVariable("id") String id) {
        return commentService.deleteCommentById(id);
    }

    @PutMapping("/UpdateComment/{id}")
    public Optional<Comment> updateByCommentId(@PathVariable("id") String id,
                                               @RequestBody CommentUpdatePayload payload) {
        return commentService.updateCommentById(id, payload);
    }
}

