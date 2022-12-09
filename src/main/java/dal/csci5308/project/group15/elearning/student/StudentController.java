package dal.csci5308.project.group15.elearning.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;

@Controller
public class StudentController {

    //This method renders all the elements when a student logs in and views their Dashboard
    @GetMapping("student/dashboard")
    public String studentDashboard(Model model) {
        //Fetch Student's currently enrolled courses

        //Fetch Student's university-level announcements

        //Fetch Student's Calendar/Upcoming Deadlines
        return "studentDashboard";
    }

   /* private HashMap<String, String> getEnrolledCourses(){

    }*/
}
