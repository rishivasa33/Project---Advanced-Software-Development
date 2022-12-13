package dal.csci5308.project.group15.elearning.factory.registerUser;

import dal.csci5308.project.group15.elearning.models.Register.User;
import dal.csci5308.project.group15.elearning.models.Register.IRegisterUserHandler;

public interface IRegisterUserFactory
{
    public User makeUser();
    public IRegisterUserHandler makeRegisterUserHandler();
}
