package dal.csci5308.project.group15.elearning.models.forum;

import java.util.List;

public class ForumTopic
{
    private String id;
    private String courseId;
    private String topic;
    private String createdBy;
    private String createdOn;

    private List<ForumTopicResponse> replyList;

    public ForumTopic()
    {

    }

    public ForumTopic(String id, String courseId, String topic, String createdBy, String createdOn, List<ForumTopicResponse> replyList) {
        this.id = id;
        this.courseId = courseId;
        this.topic = topic;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.replyList = replyList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public List<ForumTopicResponse> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ForumTopicResponse> replyList) {
        this.replyList = replyList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ForumTopic{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", topic='" + topic + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", replyList=" + replyList +
                '}';
    }
}
