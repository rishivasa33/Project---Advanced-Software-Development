package dal.csci5308.project.group15.elearning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfessorController {

    @GetMapping("/")
    public String hellomessage() {
        return "professorDashboard"; //view name
    }

    @GetMapping("/hello")
    public String helloWithParam() {
        return "professorDashboard"; //view name
    }
}
