package dal.csci5308.project.group15.elearning.controller.professor;

import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlGradedCoursePersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("professor/")
public class ProfessorDashBoardController {

    @GetMapping("dashboard")
    public String DashboardView(Model model)  {
        try {
            GradedCoursePersistence gradedCoursePersistence = GradedCoursePersistenceSingleton.GetMySqlGradedCoursePersistenceInstance();
            ArrayList<Course> course_list = gradedCoursePersistence.GetAllGradedCourses();
            ArrayList<ArrayList<String>> course_names = new ArrayList<>();
            for (Course gc : course_list) {
                ArrayList<String> course_info = new ArrayList<>();
                course_info.add(gc.GetCourseBase().GetCourseID());
                course_info.add(gc.GetCourseBase().GetName());
                course_info.add(gc.GetCourseBase().GetDescription());
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

    @GetMapping("courseDetails")
    public String CourseDetailsView(@RequestParam String courseId, Model model)
    {
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateGradedCourse("","","", 0);
        try {
            course = course.Load(courseId);
            ArrayList<CourseModule> course_module_list = course.GetCourseBase().GetAllModules();
            ArrayList<ArrayList<String>> course_module_names = new ArrayList<>();
            for (CourseModule courseModule : course_module_list) {
                ArrayList<String> course_module_info = new ArrayList<>();
                course_module_info.add(Integer.toString(courseModule.GetCourseModuleId()));
                course_module_info.add(courseModule.GetModuleName());
                course_module_names.add(course_module_info);
            }
            model.addAttribute("course_module_list", course_module_names);
            model.addAttribute("courseId", courseId);

        }
        catch(SQLException exception){
            System.out.println("Course Module Fetch Error");
        }

        return "courseDetails";
    }

    @GetMapping("courseDetails/courseModuleDetails")
    public String CourseModuleDetailsView(@RequestParam String courseModuleId, @RequestParam String courseId, Model model)
    {
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseModuleId", courseModuleId);

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("", "" , "", 10);
        try{
            course = course.Load(courseId);
            String moduleName = course.GetCourseBase().GetModuleName(Integer.parseInt(courseModuleId));
            model.addAttribute("courseModuleName", moduleName);
        }
        catch (SQLException exception){
            model.addAttribute("courseModuleName", "Error Loading Module");
        }

        return "courseModuleDetails";
    }



    @PostMapping("courseDetails/AddModule")
    public RedirectView AddModuleView(@RequestParam String courseId, @RequestParam String course_module_name, RedirectAttributes redirectAttributes)
    {
        CourseContentFactory courseContentFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactory.CreateCourseModule(course_module_name);
        try{
            courseModule.Save(courseId);
            redirectAttributes.addFlashAttribute("message", "module created successfully");
        }
        catch (SQLException exception){
            System.out.println("Course Module Creation Error");
            redirectAttributes.addFlashAttribute("message", "module creation failed");
        }

        return new RedirectView("?courseId="+courseId);
    }

    @PostMapping("create/course")
    public String CourseSubmitView(@RequestParam String course_code, @RequestParam String course_name, @RequestParam String course_description,
                                   @RequestParam int total_credits, Model model)
    {
        try {
            ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
            Course course = courseFactory.CreateGradedCourse(course_code, course_name, course_description, total_credits);
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







}


