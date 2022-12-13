package dal.csci5308.project.group15.elearning.models.forum;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.security.IAuthUser;

import java.util.Map;

public interface IForumHandler
{
    public Map<String, ForumTopic> getAllTopics(IDatabaseOperations databaseOperations, IAuthUser authUser, String courseId);
    public int createNewTopic(IDatabaseOperations databaseOperations, IAuthUser authUser, String courseId, ForumTopic topic);
    public int createNewResponse(IDatabaseOperations databaseOperations, IAuthUser authUser, Map<String, ForumTopic> forumTopicMap, ForumComment comment);
}
