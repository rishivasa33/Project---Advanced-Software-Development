package dal.csci5308.project.group15.elearning.controller.login;

import dal.csci5308.project.group15.elearning.factory.authRoleChain.AuthRoleChainFactory;
import dal.csci5308.project.group15.elearning.factory.authRoleChain.IAuthRoleChainFactory;
import dal.csci5308.project.group15.elearning.security.authChain.IAuthRoleChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("/")
    public String login()
    {
        IAuthRoleChainFactory authRoleChainFactory = AuthRoleChainFactory.instance();
        IAuthRoleChain authRoleChain = authRoleChainFactory.makeAuthRoleChain();

        return authRoleChain.getLandingPage();
    }
}
