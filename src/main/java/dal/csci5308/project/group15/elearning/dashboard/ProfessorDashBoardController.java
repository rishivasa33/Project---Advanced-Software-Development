package dal.csci5308.project.group15.elearning.dashboard;

import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistenceSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ProfessorDashBoardController {

    @GetMapping("dashboard/professor")
    public String DashboardView(Model model)
    {
        GradedCoursePersistence gradedCoursePersistence = GradedCoursePersistenceSingleton.GetMySqlGradedCoursePersistenceInstance();
        ArrayList<GradedCourse> course_list = gradedCoursePersistence.GetAllGradedCourses();
        ArrayList<ArrayList<String>> course_names = new ArrayList<>();
        for(GradedCourse gc : course_list){
            ArrayList<String> course_info =  new ArrayList<>();
            course_info.add(gc.GetCourse().GetName());
            course_info.add(gc.GetCourse().GetDescription());
            course_info.add(Integer.toString(gc.GetCredits()));
            course_names.add(course_info);
        }
        model.addAttribute("course_list", course_names);
        return "professorDashboard";
    }

}
