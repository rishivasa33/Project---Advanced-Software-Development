package dal.csci5308.project.group15.elearning.security.authChain;

import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;

public class AuthStudentChain implements IAuthRoleChain
{
    @Override
    public String getLandingPage()
    {
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

        if(authUser.isStudent())
        {
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_STUDENT_DASHBOARD");
        }

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_LOGIN");
    }
}
