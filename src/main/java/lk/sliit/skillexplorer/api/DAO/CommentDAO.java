package lk.sliit.skillexplorer.api.DAO;


import lk.sliit.skillexplorer.api.Entity.Comment;
import lk.sliit.skillexplorer.api.Entity.CommentUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class CommentDAO {

    private final CommentRepository repository;

    @Autowired
    public CommentDAO(CommentRepository repository) {
        this.repository = repository;
    }

    public Collection<Comment> getComments() {
        return repository.findAll();
    }

    public Comment createComment(Comment comment) {
        return repository.insert(comment);
    }

    public Optional<Comment> getCommentById(String id) {
        return repository.findById(id);
    }

    public Optional<Comment> deleteCommentById(String id) {
        Optional<Comment> comment = repository.findById(id);
        comment.ifPresent(repository::delete);
        return comment;
    }

    public Optional<Comment> updateCommentById(String id, CommentUpdatePayload payload) {
        Optional<Comment> comment = repository.findById(id);
        comment.ifPresent(c -> {
            c.setContent(payload.getContent());
            repository.save(c);
        });
        return comment;
    }
}
