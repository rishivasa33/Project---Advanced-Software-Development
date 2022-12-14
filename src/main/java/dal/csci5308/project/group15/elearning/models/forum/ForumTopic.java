package dal.csci5308.project.group15.elearning.models.forum;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ForumTopic
{
    private String id;
    private String courseId;
    private String topic;
    private String createdBy;
    private String createdOn;

    private List<ForumTopicResponse> replyList;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ForumTopic()
    {
        replyList = new LinkedList<>();
    }

    public ForumTopic(String id, String courseId, String topic, String createdBy, String createdOn, List<ForumTopicResponse> replyList) {
        this.id = id;
        this.courseId = courseId;
        this.topic = topic;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.replyList = replyList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public List<ForumTopicResponse> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ForumTopicResponse> replyList) {
        this.replyList = replyList;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ForumTopic{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", topic='" + topic + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", replyList=" + replyList +
                '}';
    }

    public Map<String, ForumTopic> getAllTopics(IDatabaseOperations databaseOperations, IAuthUser authUser, String courseId)
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

    public int createNewTopic(IDatabaseOperations databaseOperations, IAuthUser authUser, String courseId, ForumTopic topic)
    {
        try
        {
            IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
            return databaseOperations.create(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC"),
                    courseId, topic.getTopic(), authUser.getUsername());
        }
        catch (SQLException sqlException)
        {
            logger.error(sqlException.getMessage());
            return 0;
        }
    }
}
