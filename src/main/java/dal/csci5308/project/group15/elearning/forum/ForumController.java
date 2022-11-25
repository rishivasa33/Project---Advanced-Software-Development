package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller()
@RequestMapping("/forum")
public class ForumController
{
    private List<ForumTopic> topicList;
    private Map<String, ForumTopic> forumTopicMap;

    @GetMapping("/list")
    public String showForum(Model model )
    {
        forumTopicMap = new HashMap<>();
        topicList = new LinkedList<>();

        //  get forum details from database.
        Connection connection = Database.instance().getConnection();
        CallableStatement statement = null;

        try
        {
            statement = connection.prepareCall("call get_forum_topics_and_replies()");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                ForumTopic topic = new ForumTopic();

                topic.setId(resultSet.getString("TOPIC_ID"));
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

        return "forum";
    }
}
