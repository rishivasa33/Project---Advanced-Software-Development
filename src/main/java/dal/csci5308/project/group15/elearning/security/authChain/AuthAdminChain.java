package dal.csci5308.project.group15.elearning.security.authChain;

import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;

public class AuthAdminChain implements IAuthRoleChain
{
    @Override
    public String getLandingPage()
    {
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();

        if(authUser.isAdmin())
        {
            IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_ADMIN_DASHBOARD");
        }

        return new AuthProfessorChain().getLandingPage();
    }
}
