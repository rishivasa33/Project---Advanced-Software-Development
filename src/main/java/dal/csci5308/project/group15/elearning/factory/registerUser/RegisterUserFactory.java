package dal.csci5308.project.group15.elearning.factory.registerUser;

import dal.csci5308.project.group15.elearning.models.register.RegisterUser;

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
    public RegisterUser makeUser() {
        return new RegisterUser();
    }
}
