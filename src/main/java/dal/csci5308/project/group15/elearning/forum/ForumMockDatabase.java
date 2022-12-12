package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;

import java.util.LinkedList;
import java.util.List;

public class ForumMockDatabase implements IForumPersistence
{
    private List<ForumTopic> forumTopicList;

    public ForumMockDatabase()
    {
        forumTopicList = new LinkedList<>();
    }

    @Override
    public int createForumTopic(ForumTopic newTopic)
    {
        forumTopicList.add(newTopic);
        return 1;
    }

    @Override
    public List<ForumTopic> loadAllForumTopics()
    {
        return forumTopicList;
    }

    @Override
    public int createForumTopicResponse(String topicId, ForumTopicResponse response)
    {
        for(ForumTopic topic: forumTopicList)
        {
            if(topic.getId().equals(topicId))
            {
                topic.getReplyList().add(response);
                return 1;
            }
        }

        return 0;
    }
}
