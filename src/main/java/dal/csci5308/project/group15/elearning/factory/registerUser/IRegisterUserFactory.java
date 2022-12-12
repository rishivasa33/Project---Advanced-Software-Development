package dal.csci5308.project.group15.elearning.factory.registerUser;

import dal.csci5308.project.group15.elearning.forum.IForumHandler;
import dal.csci5308.project.group15.elearning.models.Register.User;
import dal.csci5308.project.group15.elearning.models.forum.ForumComment;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopic;
import dal.csci5308.project.group15.elearning.models.forum.ForumTopicResponse;
import dal.csci5308.project.group15.elearning.register.IRegisterUserHandler;

import java.util.List;

public interface IRegisterUserFactory
{
    public User makeUser();
    public IRegisterUserHandler makeRegisterUserHandler();
}
