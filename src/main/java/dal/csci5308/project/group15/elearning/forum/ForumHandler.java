package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.ForumFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ForumHandler implements IForumHandler
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ForumHandler()
    {   }

    @Override
    public Map<String, ForumTopic> getAllTopics(String courseId)
    {
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        List<ForumTopic> forumTopicList = ForumFactory.instance().makeForumTopicList();
        Map<String, ForumTopic> forumTopicMap = new HashMap<>();

        try
        {
            Map<String, List<Object>> resultSet = databaseOperations.read("get_forum_topics_and_replies", courseId);

            //  iterate through the resultSet and put items in forumTopicList
            for(int row = 0; row < databaseOperations.getRowCount(resultSet); row++)
            {
                ForumTopic topic = ForumFactory.instance().makeForumTopic();

                topic.setId(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC_ID", row)));
                topic.setTopic(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC", row)));
                topic.setCreatedBy(String.valueOf(databaseOperations.getValueAt(resultSet, "TOPIC_CREATED_BY", row)));
                topic.setCourseId(String.valueOf(databaseOperations.getValueAt(resultSet, "COURSE_ID", row)));

                ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

                response.setId(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY_ID", row)));
                response.setReply(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY", row)));
                response.setCreatedBy(String.valueOf(databaseOperations.getValueAt(resultSet, "REPLY_BY", row)));

                forumTopicList.add(topic);

                if(forumTopicMap.containsKey(topic.getId()))
                {
                    //  topic exists. add response to this topic
                    ForumTopic updatedTopic = forumTopicMap.get(topic.getId());
                    updatedTopic.getReplyList().add(response);
                    forumTopicMap.put(topic.getId(), updatedTopic);
                }
                else
                {
                    //  topic does not exist. create new topic and add response to this topic
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
    public int createNewTopic(String courseId, ForumTopic topic)
    {
        try
        {
            IDatabaseOperations databaseOperations = DatabaseOperations.instance();
            return databaseOperations.create("add_forum_topic", courseId, topic.getTopic(), AuthUser.getUsername());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }

    @Override
    public int createNewResponse(Map<String, ForumTopic> forumTopicMap, ForumComment comment)
    {
        String[] commentArray = comment.getComment().split(",");

        int topicIdToUpdate = getTopicIdToUpdate(commentArray);
        String commentToAdd = commentArray[topicIdToUpdate];
        ForumTopic topic = getTopicToUpdate(forumTopicMap, topicIdToUpdate);

        try
        {
            IDatabaseOperations databaseOperations = DatabaseOperations.instance();
            return databaseOperations.create("add_comment_to_topic", topic.getId(), commentToAdd, AuthUser.getUsername());
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

        return 0;
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
