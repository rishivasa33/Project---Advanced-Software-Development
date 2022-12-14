package dal.csci5308.project.group15.elearning.controller.forum;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
import dal.csci5308.project.group15.elearning.factory.forum.IForumFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;
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

        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        IForumFactory forumFactory = ForumFactory.instance();
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();
        ForumTopic forumTopic = forumFactory.makeForumTopic();

        forumTopicMap = forumTopic.getAllTopics(databaseOperations, authUser, courseId);

        model.addAttribute("forumTopicMap", forumTopicMap);
        model.addAttribute("topicList", forumFactory.makeForumTopicList());
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
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();
        String courseId = String.valueOf(model.getAttribute("courseId"));
        IForumFactory forumFactory = ForumFactory.instance();
        ForumTopicResponse response = forumFactory.makeForumTopicResponse();

        if(comment.getComment().length() > 0)
        {
            response.createNewResponse(databaseOperations, authUser, forumTopicMap, comment);
        }

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_FORUM_LIST") + "?courseId=" + courseId;
    }

    @PostMapping("/createNewTopic")
    public String createNewTopic(@ModelAttribute("newTopic") ForumTopic newTopic, Model model)
    {
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();
        IForumFactory forumFactory = ForumFactory.instance();
        ForumTopic forumTopic = forumFactory.makeForumTopic();

        String courseId = String.valueOf(model.getAttribute("courseId"));

        if(newTopic.getTopic().length() > 0)
        {
            forumTopic.createNewTopic(databaseOperations, authUser, courseId, newTopic);
        }

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_FORUM_LIST") + "?courseId=" + courseId;
    }
}