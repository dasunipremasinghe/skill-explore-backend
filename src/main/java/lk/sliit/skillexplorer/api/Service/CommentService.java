package lk.sliit.skillexplorer.api.Service;


import lk.sliit.skillexplorer.api.DAO.CommentDAO;
import lk.sliit.skillexplorer.api.Entity.Comment;
import lk.sliit.skillexplorer.api.Entity.CommentUpdatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentDAO commentDAO;

    @Autowired
    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public Collection<Comment> getComments() {
        return commentDAO.getComments();
    }

    public Comment createComment(Comment comment) {
        return commentDAO.createComment(comment);
    }

    public Optional<Comment> getCommentById(String id) {
        return commentDAO.getCommentById(id);
    }

    public Optional<Comment> deleteCommentById(String id) {
        return commentDAO.deleteCommentById(id);
    }

    public Optional<Comment> updateCommentById(String id, CommentUpdatePayload payload) {
        return commentDAO.updateCommentById(id, payload);
    }
}

