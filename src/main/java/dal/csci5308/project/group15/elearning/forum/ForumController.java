package dal.csci5308.project.group15.elearning.forum;

import dal.csci5308.project.group15.elearning.factory.forum.ForumFactory;
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
public class ForumController
{
    private List<ForumTopic> topicList;
    private Map<String, ForumTopic> forumTopicMap;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list")
    public String showForum(Model model)
    {
        logger.debug("Inside forum list showForum");
        System.out.println("Inside forum list showForum");

        topicList = new LinkedList<>();

        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();

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

        System.out.println("New Comment to add: " + comment.getComment());

        if(comment.getComment().length() > 0)
        {
            IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();
            forumHandler.createNewResponse(forumTopicMap, comment);
        }

        return "redirect:/forum/list";
    }

    @PostMapping("/createNewTopic")
    public String createNewTopic(@ModelAttribute("newTopic") ForumTopic newTopic)
    {
        IForumHandler forumHandler = ForumFactory.instance().makeForumHandler();

        if(newTopic.getTopic().length() > 0)
        {
            //  TODO: Remove hardcoded value of course
            forumHandler.createNewTopic("F22CSCI 5402", newTopic);
        }

        return "redirect:/forum/list";
    }
}