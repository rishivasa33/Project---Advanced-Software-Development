package dal.csci5308.project.group15.elearning.models.forum;

import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class ForumTopicResponse
{
    private String id;
    private String reply;
    private String createdBy;
    private String createdOn;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    public ForumTopicResponse()
    {

    }

    public ForumTopicResponse(String id, String reply, String createdBy, String createdOn) {
        this.id = id;
        this.reply = reply;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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

    @Override
    public String toString() {
        return "ForumReply{" +
                "id='" + id + '\'' +
                ", reply='" + reply + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }

    public int createNewResponse(IDatabaseOperations databaseOperations, IAuthUser authUser, Map<String, ForumTopic> forumTopicMap, ForumComment comment)
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
                    topic.getId(), commentToAdd, authUser.getUsername());
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
        Iterator<ForumTopic> iterator = forumTopicMap.values().iterator();
        ForumTopic topic = null;

        for(int i = 0; i < forumTopicMap.size(); i++)
        {
            topic = iterator.next();

            if(i == topicIdToUpdate)
            {
                break;
            }
        }

        return topic;
    }
}
