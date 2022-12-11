package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.ForumFactory;
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

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String showForum(Model model)
    {
        logger.debug("Inside forum list showForum");

        topicList = new LinkedList<>();

        IForumHandler forumHandler = new ForumHandler();

        //  TODO:   remove hardcoding of courseId
        forumTopicMap = forumHandler.getAllTopics("F22CSCI 5402");

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
        logger.debug("New Comment to add: " + comment.getComment());

        IForumHandler forumHandler = new ForumHandler();
        forumHandler.createNewResponse(forumTopicMap, comment);

        return "redirect:/forum/list";
    }

    @PostMapping("/createNewTopic")
    public String createNewTopic(@ModelAttribute("newTopic") ForumTopic newTopic)
    {
        //  TODO: Replace with factory
        IForumHandler forumHandler = new ForumHandler();
        //  TODO: Remove hardcoded value of course
        forumHandler.createNewTopic("F22CSCI 5402", newTopic);

        return "redirect:/forum/list";
    }
}