package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ForumHandlerTest
{
    @Test
    public void loadEmptyForumTopicList()
    {
        IForumPersistence mockDb = new ForumMockDatabase();
        List<ForumTopic> forumTopicList = mockDb.loadAllForumTopics();

        Assertions.assertNotNull(forumTopicList);
        Assertions.assertEquals(0, forumTopicList.size());
    }

    @Test
    public void createForumTopicListWithOneElementNoReplyList()
    {
        IForumPersistence mockDb = new ForumMockDatabase();

        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();

        forumTopic.setId("1");
        forumTopic.setTopic("Test Topic 1");
        forumTopic.setCourseId("Test Course ID");
        forumTopic.setCreatedBy("Test User");
        forumTopic.setCreatedOn("Test Date");

        int result = mockDb.createForumTopic(forumTopic);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void createForumTopicListWithOneElementOneReplyList()
    {
        IForumPersistence mockDb = new ForumMockDatabase();

        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();
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

        int result = mockDb.createForumTopic(forumTopic);

        Assertions.assertEquals(1, result);
    }

    @Test
    public void createAndLoadForumTopicList()
    {
        IForumPersistence mockDb = new ForumMockDatabase();

        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();
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

        int result1 = mockDb.createForumTopic(forumTopic);

        Assertions.assertEquals(1, result1);

        ForumTopic forumTopic2 = ForumFactory.instance().makeForumTopic();
        ForumTopicResponse response2 = ForumFactory.instance().makeForumTopicResponse();

        forumTopic.setId("2");
        forumTopic.setTopic("Test Topic 2");
        forumTopic.setCourseId("Test Course ID 2");
        forumTopic.setCreatedBy("Test User2");
        forumTopic.setCreatedOn("Test Date2");

        response.setId("2");
        response.setReply("Reply 2");
        response.setCreatedBy("Test User2");
        response.setCreatedOn("Test Date2");

        forumTopic2.getReplyList().add(response2);

        int result2 = mockDb.createForumTopic(forumTopic);

        Assertions.assertEquals(1, result2);

        List<ForumTopic> forumTopicList = mockDb.loadAllForumTopics();

        System.out.println(forumTopicList);

        Assertions.assertNotNull(forumTopicList);
        Assertions.assertEquals(2, forumTopicList.size());
    }

    @Test
    public void addResponseToTopicWhichDoesNotExist()
    {
        IForumPersistence mockDb = new ForumMockDatabase();

        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

        response.setId("1");
        response.setReply("Reply 1");
        response.setCreatedBy("Test User");
        response.setCreatedOn("Test Date");

        int result = mockDb.createForumTopicResponse("1", response);
        Assertions.assertEquals(0, result);
    }

    @Test
    public void addResponseToTopicWhichExists()
    {
        IForumPersistence mockDb = new ForumMockDatabase();

        ForumTopic forumTopic = ForumFactory.instance().makeForumTopic();

        forumTopic.setId("1");
        forumTopic.setTopic("Test Topic 1");
        forumTopic.setCourseId("Test Course ID");
        forumTopic.setCreatedBy("Test User");
        forumTopic.setCreatedOn("Test Date");

        mockDb.createForumTopic(forumTopic);

        ForumTopicResponse response = ForumFactory.instance().makeForumTopicResponse();

        response.setId("1");
        response.setReply("Reply 1");
        response.setCreatedBy("Test User");
        response.setCreatedOn("Test Date");

        int result = mockDb.createForumTopicResponse("1", response);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void testStoredProcedureConstructMethod()
    {
        String generatedQuery = DatabaseOperations.instance().prepareProceduralQuery("MY_CUSTOM_PROCEDURE", 5);
        Assertions.assertEquals("call MY_CUSTOM_PROCEDURE(?,?,?,?,?);", generatedQuery);
    }
}
