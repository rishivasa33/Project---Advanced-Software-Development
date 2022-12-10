package dal.csci5308.project.group15.elearning.factory;

import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.LinkedList;
import java.util.List;

public class ForumFactory implements IForumFactory
{
    private static ForumFactory uniqueInstance;

    private ForumFactory()
    {

    }

    public static ForumFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new ForumFactory();
        }

        return uniqueInstance;
    }

    @Override
    public ForumTopic makeForumTopic()
    {
        return new ForumTopic();
    }

    @Override
    public ForumComment makeForumComment()
    {
        return new ForumComment();
    }

    @Override
    public ForumTopicResponse makeForumTopicResponse()
    {
        return new ForumTopicResponse();
    }

    @Override
    public List<ForumTopic> makeForumTopicList()
    {
        return new LinkedList<>();
    }
}
