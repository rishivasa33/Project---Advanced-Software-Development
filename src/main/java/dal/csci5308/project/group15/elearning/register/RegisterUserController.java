package dal.csci5308.project.group15.elearning.register;

import dal.csci5308.project.group15.elearning.factory.registerUser.RegisterUserFactory;
import dal.csci5308.project.group15.elearning.models.Register.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/registerUser")
public class RegisterUserController
{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public String loadRegisterPage(Model model)
    {
        User newUser = getInitUser();
        model.addAttribute("user", newUser);
        model.addAttribute("programMap", newUser.getProgramMap());

        return "registerUser";
    }

    @PostMapping("/add")
    public String registerNewUser(@ModelAttribute("user") User user, Model model)
    {
        System.out.println(user);

        IRegisterUserHandler registerUserHandler = RegisterUserFactory.instance().makeRegisterUserHandler();
        int result = registerUserHandler.createNewUser(user);

        if(result > 0)
        {
            User newUser = getInitUser();
            model.addAttribute("user", newUser);
            model.addAttribute("programMap", newUser.getProgramMap());
            model.addAttribute("message", "success");
            logger.debug("User - " + user.getFirstName() + " " + user.getLastName() + " registered successfully.");
        }
        else
        {
            model.addAttribute("message", "fail");
            logger.error("User - " + user.getFirstName() + " " + user.getLastName() + " failed to register.");
        }

        return "registerUser";
    }

    private User getInitUser()
    {
        IRegisterUserHandler registerUserHandler = RegisterUserFactory.instance().makeRegisterUserHandler();
        User newUser = RegisterUserFactory.instance().makeUser();
        Map<String, String> programMap = registerUserHandler.getAllProgramList();
        newUser.setProgramMap(programMap);
        return newUser;
    }
}
