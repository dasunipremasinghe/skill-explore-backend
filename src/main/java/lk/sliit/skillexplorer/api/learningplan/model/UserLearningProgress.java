package lk.sliit.skillexplorer.api.learningplan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_learning_progress")
public class UserLearningProgress {

    @Id
    private String id;

    private String userId;
    private String learningPlanId;

    // Each topic in the plan and its completion status
    private List<TopicProgress> topicProgressList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopicProgress {
        private String topicName;
        private boolean completed;
    }
}
