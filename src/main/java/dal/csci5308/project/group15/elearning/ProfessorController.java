package dal.csci5308.project.group15.elearning;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ProfessorController {

    @GetMapping("/")
    public RedirectView redirectWithUsingRedirectView(
            RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("/dashboard/professor");
    }
//     return new RedirectView("redirectedUrl");
//    public String hellomessage() {
//        return "professorDashboard"; //view name
//    }

    @GetMapping("/hello")
    public String helloWithParam() {
        return "professorDashboard"; //view name
    }
}
