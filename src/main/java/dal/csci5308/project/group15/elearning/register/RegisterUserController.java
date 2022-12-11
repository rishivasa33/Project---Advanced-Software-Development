package dal.csci5308.project.group15.elearning.register;

import dal.csci5308.project.group15.elearning.factory.registerUser.RegisterUserFactory;
import dal.csci5308.project.group15.elearning.models.Register.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registerUser")
public class RegisterUserController
{
    @GetMapping("")
    public String loadRegisterPage(Model model)
    {
        model.addAttribute("user", new User());
        return "registerUser";
    }

    @PostMapping("/add")
    public String registerNewUser(@ModelAttribute("user") User user)
    {
        System.out.println(user);

        IRegisterUserHandler registerUserHandler = RegisterUserFactory.instance().makeRegisterUserHandler();
        registerUserHandler.createNewUser(user);

        return "registerUser";
    }
}
