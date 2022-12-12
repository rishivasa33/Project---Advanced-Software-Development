package dal.csci5308.project.group15.elearning.login;

import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController
{
    @RequestMapping("/")
    public String login()
    {
        if(AuthUser.isAdmin())
        {
            return "redirect:/professor";
        }
        else if(AuthUser.isStudent())
        {
            return "redirect:/student/dashboard";
        }

        return "redirect:/student/dashboard";
    }
}
