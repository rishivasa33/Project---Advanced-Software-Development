package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import java.util.Map;

public interface IForumHandler
{
    public Map<String, ForumTopic> getAllTopics(IDatabaseOperations databaseOperations, String courseId);
    public int createNewTopic(IDatabaseOperations databaseOperations, String courseId, ForumTopic topic);
    public int createNewResponse(IDatabaseOperations databaseOperations, Map<String, ForumTopic> forumTopicMap, ForumComment comment);
}
