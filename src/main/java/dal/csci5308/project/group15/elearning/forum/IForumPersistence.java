package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.List;

public interface IForumPersistence
{
    public int createForumTopic(ForumTopic topic);
    public List<ForumTopic> loadAllForumTopics();
    public int createForumTopicResponse(String topicId, ForumTopicResponse response);
}
