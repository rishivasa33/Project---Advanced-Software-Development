package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;

public class AuthStudentState implements IAuthRoleState
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
