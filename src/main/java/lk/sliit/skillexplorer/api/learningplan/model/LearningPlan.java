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
@Document(collection = "learning_plans")
public class LearningPlan {

    @Id
    private String id;

    private String title;
    private String description;
    private String userId; // email or UID of the creator
    private boolean archived;

    private List<Topic> topics;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Topic {
        private String title;
        private List<Resource> resources;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Resource {
        private String name;
        private String url;
        private int estimatedTimeHours;
    }
}
