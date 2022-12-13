package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.factory.forum.IForumFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequestMapping("/forum")
@SessionAttributes({"courseId"})
public class ForumController
{
    private List<ForumTopic> topicList;
    private Map<String, ForumTopic> forumTopicMap;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String showForum(Model model, @RequestParam("courseId") String courseId)
    {
        logger.debug("Inside forum list showForum");

        topicList = new LinkedList<>();

        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        IForumFactory forumFactory = ForumFactory.instance();

        forumTopicMap = forumHandler.getAllTopics(databaseOperations, courseId);

        model.addAttribute("forumTopicMap", forumTopicMap);
        model.addAttribute("topicList", topicList);
        model.addAttribute("forumComment", forumFactory.makeForumComment());
        model.addAttribute("newTopic", forumFactory.makeForumTopic());
        model.addAttribute("courseId", courseId);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_FORUM_LIST");
    }

    @PostMapping("/postForumComment")
    public String addNewCommentToTopic(@ModelAttribute("forumComment") ForumComment comment,
                                       Model model)
    {
        logger.debug("New Comment to add: " + comment.getComment());

        IDatabaseOperations databaseOperations = DatabaseOperations.instance();

        if(comment.getComment().length() > 0)
        {
            IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
            forumHandler.createNewResponse(databaseOperations, forumTopicMap, comment);
        }

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_FORUM_LIST");
    }

    @PostMapping("/createNewTopic")
    public String createNewTopic(@ModelAttribute("newTopic") ForumTopic newTopic, Model model)
    {
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();

        String courseId = String.valueOf(model.getAttribute("courseId"));

        if(newTopic.getTopic().length() > 0)
        {
            forumHandler.createNewTopic(databaseOperations,courseId, newTopic);
        }

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_FORUM_LIST") + "?courseId=" + courseId;
    }
}