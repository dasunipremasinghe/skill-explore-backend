package lk.sliit.skillexplorer.api.learningplan.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "learning_plans")
public class LearningPlan {

    @Id
    private String id;
    private String userId;
    private String title;
    private String description;
    private List<String> topics;
    private List<String> resources;
    private boolean archived;

    public LearningPlan() {}

    public LearningPlan(String userId, String title, String description, List<String> topics, List<String> resources) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.topics = topics;
        this.resources = resources;
        this.archived = false;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
