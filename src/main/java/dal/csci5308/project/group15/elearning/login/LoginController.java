package dal.csci5308.project.group15.elearning.login;

import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("/")
    public String login()
    {
        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

        if(AuthUser.isAdmin())
        {
            System.out.println("admin");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_PROFESSOR_DASHBOARD");
        }
        else if(AuthUser.isStudent())
        {
            System.out.println("student");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("REDIRECT_STUDENT_DASHBOARD");
        }

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_LOGIN");
    }
}
