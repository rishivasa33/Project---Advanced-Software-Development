package dal.csci5308.project.group15.elearning.models.forum;

public class ForumTopicResponse
{
    private String id;
    private String reply;
    private String createdBy;
    private String createdOn;

    public ForumTopicResponse()
    {

    }

    public ForumTopicResponse(String id, String reply, String createdBy, String createdOn) {
        this.id = id;
        this.reply = reply;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    @Override
    public String toString() {
        return "ForumReply{" +
                "id='" + id + '\'' +
                ", reply='" + reply + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
