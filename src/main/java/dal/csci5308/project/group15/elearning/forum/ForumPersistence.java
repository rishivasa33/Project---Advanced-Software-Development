package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.List;

public class ForumPersistence implements IForumPersistence
{
    @Override
    public int createForumTopic(ForumTopic topic)
    {
        return 0;
    }

    @Override
    public List<ForumTopic> loadAllForumTopics()
    {
        return null;
    }

    @Override
    public int createForumTopicResponse(String topicId, ForumTopicResponse response)
    {
        return 0;
    }
}
