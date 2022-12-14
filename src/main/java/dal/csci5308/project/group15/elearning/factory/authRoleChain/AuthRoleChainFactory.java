package dal.csci5308.project.group15.elearning.factory.authRoleChain;

import dal.csci5308.project.group15.elearning.security.authChain.AuthRoleChain;
import dal.csci5308.project.group15.elearning.security.authChain.IAuthRoleChain;

public class AuthRoleChainFactory implements IAuthRoleChainFactory
{
    private static AuthRoleChainFactory uniqueInstance;

    private AuthRoleChainFactory(){

    }

    public static AuthRoleChainFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new AuthRoleChainFactory();
        }

        return uniqueInstance;
    }

    @Override
    public IAuthRoleChain makeAuthRoleChain()
    {
        return new AuthRoleChain();
    }
}
