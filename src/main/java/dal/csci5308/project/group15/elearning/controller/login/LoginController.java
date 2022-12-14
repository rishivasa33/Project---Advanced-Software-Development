package dal.csci5308.project.group15.elearning.controller.login;

import dal.csci5308.project.group15.elearning.security.AuthUserState;
import dal.csci5308.project.group15.elearning.security.IAuthRoleState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("/")
    public String login()
    {
        IAuthRoleState authRoleState = new AuthUserState();
        return authRoleState.getLandingPage();
    }
}
