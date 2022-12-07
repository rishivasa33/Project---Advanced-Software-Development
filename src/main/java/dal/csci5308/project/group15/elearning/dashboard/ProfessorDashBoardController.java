package dal.csci5308.project.group15.elearning.dashboard;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlGradedCoursePersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class ProfessorDashBoardController {

    @GetMapping("dashboard/professor")
    public String DashboardView(Model model)  {
        try {
            GradedCoursePersistence gradedCoursePersistence = GradedCoursePersistenceSingleton.GetMySqlGradedCoursePersistenceInstance();
            ArrayList<GradedCourse> course_list = gradedCoursePersistence.GetAllGradedCourses();
            ArrayList<ArrayList<String>> course_names = new ArrayList<>();
            for (GradedCourse gc : course_list) {
                ArrayList<String> course_info = new ArrayList<>();
                course_info.add(gc.GetCourse().GetCourseID());
                course_info.add(gc.GetCourse().GetName());
                course_info.add(gc.GetCourse().GetDescription());
                course_info.add(Integer.toString(gc.GetCredits()));
                course_names.add(course_info);
            }
            model.addAttribute("course_list", course_names);
        }
        catch (SQLException sqlException){
            model.addAttribute("course_list", null);
        }
        return "professorDashboard";
    }

    @GetMapping("create/course")
    public String CreateCourseView(Model model)
    {
        return "createCourse";
    }

    @PostMapping("create/course")
    public String CourseSubmitView(@RequestParam String course_code, @RequestParam String course_name, @RequestParam String course_description,
                                   @RequestParam int total_credits, Model model)
    {
        try {
            CourseFactory courseFactory = new CourseFactory();
            GradedCourse course = courseFactory.CreateGradedCourse(course_code, course_name, course_description, total_credits);
            MySqlGradedCoursePersistence mySqlGradedCoursePersistence = GradedCoursePersistenceSingleton.GetMySqlGradedCoursePersistenceInstance();
            course.Save();
            model.addAttribute("course_created", true);
            return "courseCreationSuccess";
        }
        catch (SQLException exception){
            model.addAttribute("course_created", null);
            return "courseCreationSuccess";
        }
    }

    @GetMapping("add/courseContent")
    public String AddCourseContentView(Model model)
    {
        return "createCourseContent";
    }

    @PostMapping("add/courseContent")
    public String CourseContentSubmitView(@RequestParam String course_content_heading, @RequestParam String course_content_text, Model model)
    {
        try {
            CourseContentFactory courseContentFactory = new CourseContentFactory();
            TextCourseContent textCourseContent = courseContentFactory.CreateTextCourseContent(course_content_heading, course_content_text);
            textCourseContent.Save();
            model.addAttribute("course_content_added", true);
            return "AddContentSuccess";
        }
        catch (Exception exception){
            System.out.println("error happened in course content creation");
            model.addAttribute("course_content_added", null);
            return "AddContentSuccess";
        }
    }



}
