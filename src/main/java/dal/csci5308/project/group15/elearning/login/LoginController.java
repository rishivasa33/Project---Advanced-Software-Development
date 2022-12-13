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
        System.out.println("inside login controller");

        if(AuthUser.isAdmin())
        {
            System.out.println("admin");
            return "redirect:/professor";
        }
        else if(AuthUser.isStudent())
        {
            System.out.println("student");
            return "redirect:/student/dashboard";
        }

        return "redirect:/login";
    }
}
