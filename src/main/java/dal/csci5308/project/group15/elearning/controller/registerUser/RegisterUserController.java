package dal.csci5308.project.group15.elearning.controller.registerUser;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.registerUser.RegisterUserFactory;
import dal.csci5308.project.group15.elearning.models.register.RegisterUser;
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
        RegisterUser newUser = getInitUser();
        model.addAttribute("user", newUser);
        model.addAttribute("programMap", newUser.getProgramMap());

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_REGISTER_USER");
    }

    @PostMapping("/add")
    public String registerNewUser(@ModelAttribute("user") RegisterUser user, Model model)
    {
        RegisterUser registerUser = RegisterUserFactory.instance().makeUser();
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();

        int result = registerUser.createNewUser(databaseOperations, user);

        if(result > 0)
        {
            RegisterUser newUser = getInitUser();
            model.addAttribute("user", newUser);

            if(newUser.getProgramMap() == null)
            {
                model.addAttribute("programMap", null);
            }
            else
            {
                model.addAttribute("programMap", newUser.getProgramMap());
            }

            model.addAttribute("message", "success");
            logger.debug("User - " + user.getFirstName() + " " + user.getLastName() + " registered successfully.");
        }
        else
        {
            model.addAttribute("message", "fail");
            logger.error("User - " + user.getFirstName() + " " + user.getLastName() + " failed to register.");
        }

        IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_REGISTER_USER");
    }

    private RegisterUser getInitUser()
    {
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        RegisterUser newUser = RegisterUserFactory.instance().makeUser();

        Map<String, String> programMap = newUser.getAllProgramList(databaseOperations);
        newUser.setProgramMap(programMap);

        return newUser;
    }
}