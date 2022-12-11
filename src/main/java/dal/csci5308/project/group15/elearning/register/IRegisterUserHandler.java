package dal.csci5308.project.group15.elearning.register;

import dal.csci5308.project.group15.elearning.models.Register.User;

public interface IRegisterUserHandler
{
    public int createNewUser(User user);
}
