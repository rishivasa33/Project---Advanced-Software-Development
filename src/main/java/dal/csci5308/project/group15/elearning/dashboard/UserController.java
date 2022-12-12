package dal.csci5308.project.group15.elearning.dashboard;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class UserController
{
    private User user;

    @GetMapping("/login")
    public String showLoginPage(Model model)
    {
        System.out.println("inside showLoginPage");
        user = new User();
        model.addAttribute("user", user);

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password)
    {
        System.out.println("inside login after submit");
        System.out.println("username: " + username);

        Connection connection = Database.instance().getConnection();

        PreparedStatement statement = null;
        try
        {
            statement = connection.prepareStatement("select * from user where username = ? and password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                user.setId(resultSet.getString("ID"));
                user.setEmail(resultSet.getString("EMAIL"));
                user.setFirstName(resultSet.getString("FIRST_NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));

                System.out.println("Logged in User: " + user.toString());
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }


        return "redirect:dashboard/professor";
    }

    @GetMapping("/user")
    public String user()
    {
        return "<h1>WELCOME USER</h1>";
    }

    @GetMapping("/admin")
    public String admin()
    {
        return "<h1>WELCOME ADMIN</h1>";
    }
}