package dal.csci5308.project.group15.elearning.factory.authUser;

import dal.csci5308.project.group15.elearning.security.AuthUser;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import dal.csci5308.project.group15.elearning.security.MockAuthUser;

public class AuthUserFactory implements IAuthFactory
{
    private static AuthUserFactory uniqueInstance;

    private AuthUserFactory()
    {

    }

    public static AuthUserFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new AuthUserFactory();
        }

        return uniqueInstance;
    }

    @Override
    public IAuthUser makeAuthUser()
    {
        return new AuthUser();
    }

    @Override
    public IAuthUser makeMockAuthUser()
    {
        return new MockAuthUser();
    }
}
