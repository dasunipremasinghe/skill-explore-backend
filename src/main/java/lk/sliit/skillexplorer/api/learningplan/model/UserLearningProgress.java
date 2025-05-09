package lk.sliit.skillexplorer.api.learningplan.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    private List<TopicProgress> topicProgressList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TopicProgress {
        private String topicName;
        private boolean completed;
        private List<ResourceProgress> resourceProgressList = new ArrayList<>();

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ResourceProgress {
            private String resourceName;
            private boolean completed;
        }
    }
}

