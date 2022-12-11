package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.ForumFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ForumHandler implements IForumHandler
{
    @Value("${STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT}")
    private String STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT;

    @Value("${STORED_PROCEDURE_FORUM_GET_FORUM_LIST}")
    private String STORED_PROCEDURE_FORUM_GET_FORUM_LIST;

    @Value("${STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC}")
    private String STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map<String, ForumTopic> getAllTopics()
    {
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        List<ForumTopic> forumTopicList = ForumFactory.instance().makeForumTopicList();
        Map<String, ForumTopic> forumTopicMap = new HashMap<>();

        try
        {
            //  TODO:   remove hardcoding of courseId
            Map<String, List<Object>> resultSet = databaseOperations.read(STORED_PROCEDURE_FORUM_GET_FORUM_LIST, "F22CSCI 5402");

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
        }

        return forumTopicMap;
    }

    @Override
    public int createNewTopic(ForumTopic topic)
    {
        try
        {
            IDatabaseOperations databaseOperations = DatabaseOperations.instance();
            return databaseOperations.create(STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC, "F22CSCI 5402", topic.getTopic(), AuthUser.getUsername());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }

    @Override
    public int createNewResponse(String topicId, ForumTopicResponse response)
    {
        return 0;
    }
}
