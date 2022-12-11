package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import java.util.Map;

public interface IForumHandler
{
    public Map<String, ForumTopic> getAllTopics();
    public int createNewTopic(ForumTopic topic);
    public int createNewResponse(String topicId, ForumTopicResponse response);
}
