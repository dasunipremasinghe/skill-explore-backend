package lk.sliit.skillexplorer.api.DAO;


import lk.sliit.skillexplorer.api.Entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
}

