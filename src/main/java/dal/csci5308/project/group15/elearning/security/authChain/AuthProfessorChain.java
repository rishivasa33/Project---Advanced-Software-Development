package dal.csci5308.project.group15.elearning.security.authChain;

import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;

public class AuthProfessorChain implements IAuthRoleChain
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

        return new AuthStudentChain().getLandingPage();
    }
}
