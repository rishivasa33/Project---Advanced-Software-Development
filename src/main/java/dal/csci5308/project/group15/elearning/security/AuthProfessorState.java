package dal.csci5308.project.group15.elearning.security;

import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;

public class AuthProfessorState implements IAuthRoleState
{
    @Override
    public String getLandingPage()
    {
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();

        if(authUser.isProfessor())
        {
            IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_PROFESSOR_DASHBOARD");
        }

        return new AuthStudentState().getLandingPage();
    }
}
