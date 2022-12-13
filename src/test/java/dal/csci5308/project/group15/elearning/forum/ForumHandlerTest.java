package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserFactory;
import dal.csci5308.project.group15.elearning.factory.authUser.IAuthFactory;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.persistence.mockDb.forum.ForumMockDatabase;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ForumHandlerTest
{
    @Test
    public void loadEmptyForumTopicList()
    {
        IDatabaseOperations mockDb = new ForumMockDatabase();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IAuthFactory authFactory = AuthUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();

        Map<String, ForumTopic> forumTopicMap = forumHandler.getAllTopics(mockDb, mockAuthUser, "1");

        Assertions.assertNotNull(forumTopicMap);
        Assertions.assertEquals(0, forumTopicMap.size());
    }

    @Test
    public void createForumTopicListWithOneElementNoReplyList()
    {
        IDatabaseOperations mockDb = new ForumMockDatabase();
        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IAuthFactory authFactory = AuthUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();

        forumTopic.setId("1");
        forumTopic.setTopic("Test Topic 1");
        forumTopic.setCourseId("Test Course ID");
        forumTopic.setCreatedBy("Test User");
        forumTopic.setCreatedOn("Test Date");

        int result = forumHandler.createNewTopic(mockDb, mockAuthUser,"COURSE_1", forumTopic);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void createForumTopicListWithOneElementOneReplyList()
    {
        IDatabaseOperations mockDb = new ForumMockDatabase();
        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IAuthFactory authFactory = AuthUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();
        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

        forumTopic.setId("1");
        forumTopic.setTopic("Test Topic 1");
        forumTopic.setCourseId("Test Course ID");
        forumTopic.setCreatedBy("Test User");
        forumTopic.setCreatedOn("Test Date");

        response.setId("1");
        response.setReply("Reply 1");
        response.setCreatedBy("Test User");
        response.setCreatedOn("Test Date");

        forumTopic.getReplyList().add(response);

        int result = forumHandler.createNewTopic(mockDb, mockAuthUser, "COURSE_2", forumTopic);

        Assertions.assertEquals(1, result);

        Map<String, ForumTopic> forumTopicMap = forumHandler.getAllTopics(mockDb, mockAuthUser, "COURSE_2");

        Assertions.assertEquals(1, forumTopicMap.size());
    }

    @Test
    public void createAndLoadForumTopicList()
    {
        IDatabaseOperations mockDb = new ForumMockDatabase();
        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IAuthFactory authFactory = AuthUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();
        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

        forumTopic.setId("1");
        forumTopic.setTopic("Test Topic 1");
        forumTopic.setCourseId("Test Course ID");
        forumTopic.setCreatedBy("Test User");
        forumTopic.setCreatedOn("Test Date");

        response.setId("1");
        response.setReply("Reply 1");
        response.setCreatedBy("Test User");
        response.setCreatedOn("Test Date");

        forumTopic.getReplyList().add(response);

        int result = forumHandler.createNewTopic(mockDb, mockAuthUser, "COURSE_1", forumTopic);

        Assertions.assertEquals(1, result);

        ForumTopicResponse response2 = ForumFactory.instance().makeForumTopicResponse();

        response.setId("2");
        response.setReply("Reply 2");
        response.setCreatedBy("Test User2");
        response.setCreatedOn("Test Date2");

        forumTopic.getReplyList().add(response2);

        int result2 = forumHandler.createNewTopic(mockDb, mockAuthUser, "COURSE_1", forumTopic);

        Assertions.assertEquals(1, result2);

        Map<String, ForumTopic> forumTopicMap = forumHandler.getAllTopics(mockDb, mockAuthUser, "COURSE_1");

        System.out.println(forumTopicMap);

        Assertions.assertNotNull(forumTopicMap);
        Assertions.assertEquals(2, forumTopicMap.size());
    }

    @Test
    public void addCommentToTopic()
    {
        IDatabaseOperations mockDb = new ForumMockDatabase();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IAuthFactory authFactory = AuthUserFactory.instance();
        IAuthUser mockAuthUser = authFactory.makeMockAuthUser();
        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();
        ForumTopic topic = ForumFactory.instance().makeForumTopic();
        ForumComment comment = ForumFactory.instance().makeForumComment();

        comment.setComment("myComment");

        topic.setId("1");
        topic.setTopic("Topic");

        response.setId("1");
        response.setReply("Reply 1");
        response.setCreatedBy("Test User");
        response.setCreatedOn("Test Date");

        topic.getReplyList().add(response);

        Map<String, ForumTopic> forumTopicMap = new HashMap<>();
        forumTopicMap.put(topic.getId(), topic);

        int result = forumHandler.createNewResponse(mockDb, mockAuthUser, forumTopicMap, comment);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testStoredProcedureConstructMethod()
    {
        String generatedQuery = DatabaseOperations.instance().prepareProceduralQuery("MY_CUSTOM_PROCEDURE", 5);
        Assertions.assertEquals("call MY_CUSTOM_PROCEDURE(?,?,?,?,?);", generatedQuery);
    }
}