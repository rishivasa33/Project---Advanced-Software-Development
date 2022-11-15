package dal.csci5308.project.group15.elearning.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class DashBoardController {

    @GetMapping("/dashboard")
    public String DashboardView()
    {
        return "Dashboard!";
    }

}
