package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

public class ForumHandler implements IForumHandler
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ForumHandler()
    {   }

    @Override
    public Map<String, ForumTopic> getAllTopics(IDatabaseOperations databaseOperations, String courseId)
    {
        List<ForumTopic> forumTopicList = ForumFactory.instance().makeForumTopicList();
        Map<String, ForumTopic> forumTopicMap = new HashMap<>();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

        try
        {
            Map<String, List<Object>> resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_FORUM_GET_FORUM_LIST"), courseId);

            for(int row = 0; row < databaseOperations.getRowCount(resultSet); row++)
            {
                ForumTopic topic = ForumFactory.instance().makeForumTopic();

                topic.setId(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC_ID", row)));
                topic.setTopic(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC", row)));
                topic.setCreatedBy(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC_CREATED_BY", row)));
                topic.setCourseId(String.valueOf(databaseOperations.getValueAt(resultSet, "COURSE_ID", row)));
                topic.setCreatedOn(String.valueOf(databaseOperations.getValueAt(resultSet, "CREATED_ON", row)));

                ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

                response.setId(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY_ID", row)));
                response.setReply(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY", row)));
                response.setCreatedBy(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY_BY", row)));
                response.setCreatedOn(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY_CREATED_ON", row)));

                forumTopicList.add(topic);

                if(forumTopicMap.containsKey(topic.getId()))
                {
                    ForumTopic updatedTopic = forumTopicMap.get(topic.getId());
                    updatedTopic.getReplyList().add(response);
                    forumTopicMap.put(topic.getId(), updatedTopic);
                }
                else
                {
                    topic.setReplyList(new LinkedList<>());
                    topic.getReplyList().add(response);
                    forumTopicMap.put(topic.getId(), topic);
                }
            }
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            sqlException.printStackTrace();
        }

        return forumTopicMap;
    }

    @Override
    public int createNewTopic(IDatabaseOperations databaseOperations, String courseId, ForumTopic topic)
    {
        try
        {
            IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
            return databaseOperations.create(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC"),
                    courseId, topic.getTopic(), AuthUser.getUsername());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }

    @Override
    public int createNewResponse(IDatabaseOperations databaseOperations, Map<String, ForumTopic> forumTopicMap, ForumComment comment)
    {
        String[] commentArray = comment.getComment().split(",");

        int topicIdToUpdate = getTopicIdToUpdate(commentArray);

        if(topicIdToUpdate == -1)
        {
            return -1;
        }

        String commentToAdd = commentArray[topicIdToUpdate];
        ForumTopic topic = getTopicToUpdate(forumTopicMap, topicIdToUpdate);
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

        try
        {
            return databaseOperations.create(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT"),
                    topic.getId(), commentToAdd, AuthUser.getUsername());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return -1;
        }
    }

    private int getTopicIdToUpdate(String[] commentArray)
    {
        for(int i = 0; i < commentArray.length; i++)
        {
            if(commentArray[i].length() > 0)
            {
                return i;
            }
        }

        return -1;
    }

    private ForumTopic getTopicToUpdate(Map<String, ForumTopic> forumTopicMap, int topicIdToUpdate)
    {
        Iterator iterator = forumTopicMap.values().iterator();
        ForumTopic topic = null;

        for(int i = 0; i < forumTopicMap.size(); i++)
        {
            topic = (ForumTopic) iterator.next();

            if(i == topicIdToUpdate)
            {
                break;
            }
        }

        return topic;
    }
}
