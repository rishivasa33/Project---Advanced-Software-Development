package dal.csci5308.project.group15.elearning.factory.registerUser;

import dal.csci5308.project.group15.elearning.models.Register.User;
import dal.csci5308.project.group15.elearning.models.Register.IRegisterUserHandler;
import dal.csci5308.project.group15.elearning.models.Register.RegisterUserHandler;

public class RegisterUserFactory implements IRegisterUserFactory
{
    private static RegisterUserFactory uniqueInstance;

    private RegisterUserFactory()
    {

    }

    public static RegisterUserFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new RegisterUserFactory();
        }

        return uniqueInstance;
    }

    @Override
    public User makeUser() {
        return new User();
    }

    @Override
    public IRegisterUserHandler makeRegisterUserHandler() {
        return new RegisterUserHandler();
    }
}
