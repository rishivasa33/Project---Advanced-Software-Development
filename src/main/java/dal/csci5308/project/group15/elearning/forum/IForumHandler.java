package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import java.util.Map;

public interface IForumHandler
{
    public Map<String, ForumTopic> getAllTopics(String courseId);
    public int createNewTopic(String courseId, ForumTopic topic);
    public int createNewResponse(Map<String, ForumTopic> forumTopicMap, ForumComment comment);
}
