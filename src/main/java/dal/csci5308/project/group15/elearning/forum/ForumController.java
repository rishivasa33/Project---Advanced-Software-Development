package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.*;
import java.util.*;

@Controller
@RequestMapping("/forum")
public class ForumController
{
    private List<ForumTopic> topicList;
    private Map<String, ForumTopic> forumTopicMap;

    @Value("${STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT}")
    private String STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT;

    @Value("${STORED_PROCEDURE_FORUM_GET_FORUM_LIST}")
    private String STORED_PROCEDURE_FORUM_GET_FORUM_LIST;

    @Value("${STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC}")
    private String STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String showForum(Model model)
    {
        logger.error("Inside forum list showForum");

        forumTopicMap = new HashMap<>();
        topicList = new LinkedList<>();

        //  get forum details from database.
        Connection connection = Database.instance().getConnection();
        CallableStatement statement = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.toString());

        try
        {
            statement = connection.prepareCall(STORED_PROCEDURE_FORUM_GET_FORUM_LIST);

            //  TODO: Replace the hardcoded course ID with a variable
            statement.setString(1, "F22CSCI 5402");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                ForumTopic topic = new ForumTopic();

                topic.setId(resultSet.getString("TOPIC_ID"));
                topic.setCourseId(resultSet.getString("COURSE_ID"));
                topic.setTopic(resultSet.getString("TOPIC"));
                topic.setCreatedBy(resultSet.getString("TOPIC_CREATED_BY"));

                topicList.add(topic);

                ForumTopicResponse response = new ForumTopicResponse();
                response.setId(resultSet.getString("REPLY_ID"));
                response.setReply(resultSet.getString("REPLY"));
                response.setCreatedBy(resultSet.getString("REPLY_BY"));

                if(forumTopicMap.containsKey(topic.getId()))
                {
                    //  topic does not exist. create new topic and add response to this topic
                    ForumTopic updatedTopic = forumTopicMap.get(topic.getId());
                    updatedTopic.getReplyList().add(response);

                    forumTopicMap.put(topic.getId(), updatedTopic);
                }
                else
                {
                    //  topic exists. add response to this topic
                    topic.setReplyList(new LinkedList<>());
                    topic.getReplyList().add(response);

                    forumTopicMap.put(topic.getId(), topic);
                }
            }

            connection.close();

            System.out.println(forumTopicMap);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

        model.addAttribute("forumTopicMap", forumTopicMap);
        model.addAttribute("topicList", topicList);
        model.addAttribute("forumComment", new ForumComment());
        model.addAttribute("newTopic", new ForumTopic());

        return "forumList";
    }

    @PostMapping("/postForumComment")
    public String addNewCommentToTopic(@ModelAttribute("forumComment") ForumComment comment,
                                       Model model)
    {
        System.out.println(comment.getComment());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        //  comments are comma separated. have to split and then identify where the value
        //  is not blank. the first occurrence is where we will focus on.
        //  Once we find the first string, we have capture the index and the stirng value
        //  and update that value (insert) that value in DB by passing that value in DB
        //  with the topic ID.

        String commentToAdd = "";
        int topicIdToUpdate = -1;

        String[] commentArray = comment.getComment().split(",");

        for(int i = 0; i < commentArray.length; i++)
        {
            if(commentArray[i].length() > 0)
            {
                topicIdToUpdate = i;
                commentToAdd = commentArray[i];
                break;
            }
        }

        System.out.println("Topic ID to update: " + topicIdToUpdate);
        System.out.println("Comment to Add: " + commentToAdd);

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

        try (
                Connection connection = Database.instance().getConnection();
                CallableStatement statement = connection.prepareCall(STORED_PROCEDURE_FORUM_INSERT_NEW_COMMENT);
            )
        {
            statement.setString(1, topic.getId());
            statement.setString(2, commentToAdd);
            statement.setString(3, AuthUser.getUsername());

            int updateResult = statement.executeUpdate();

            if(updateResult > 0)
            {
                connection.commit();
                System.out.println("Comment added successfully.");
            }
            else
            {
                connection.rollback();
                System.out.println("No rows updated");
            }
        }
        catch (SQLException sqlException)
        {
            System.out.println("Error adding comment: " + sqlException.getMessage());
        }

        return "redirect:/forum/list";
    }

    @PostMapping("/createNewTopic")
    public String createNewTopic(@ModelAttribute("newTopic") ForumTopic newTopic)
    {
        System.out.println("New Topic: " + newTopic.toString());
        //  insert new topic to DB corresponding to the current course instance (fetched from the session)

        try(
                Connection connection = Database.instance().getConnection();
                CallableStatement statement = connection.prepareCall(STORED_PROCEDURE_FORUM_ADD_NEW_TOPIC);
            )
        {
            statement.setString(1, "F22CSCI 5402");
            statement.setString(2, newTopic.getTopic());
            statement.setString(3, AuthUser.getUsername());

            int result = statement.executeUpdate();

            if(result > 0)
            {
                connection.commit();
            }
            else
            {
                connection.rollback();
            }
        }
        catch (SQLException sqlException)
        {
            logger.error("SQL Exception: " + sqlException.getMessage());
        }

        return "redirect:/forum/list";
    }
}