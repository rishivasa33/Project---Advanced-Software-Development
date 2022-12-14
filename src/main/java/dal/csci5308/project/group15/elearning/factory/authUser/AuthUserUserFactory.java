package dal.csci5308.project.group15.elearning.factory.authUser;

import dal.csci5308.project.group15.elearning.security.AuthUser;
import dal.csci5308.project.group15.elearning.security.IAuthUser;
import dal.csci5308.project.group15.elearning.security.AuthUserMock;

public class AuthUserUserFactory implements IAuthUserFactory
{
    private static AuthUserUserFactory uniqueInstance;

    private AuthUserUserFactory()
    {

    }

    public static AuthUserUserFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new AuthUserUserFactory();
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
        return new AuthUserMock();
    }
}
