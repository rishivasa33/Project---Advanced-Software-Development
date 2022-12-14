package dal.csci5308.project.group15.elearning.factory.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.List;

public interface IForumFactory
{
    public ForumTopic makeForumTopic();
    public ForumComment makeForumComment();
    public ForumTopicResponse makeForumTopicResponse();
    public List<ForumTopic> makeForumTopicList();
}
