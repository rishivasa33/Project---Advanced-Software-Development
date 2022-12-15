package dal.csci5308.project.group15.elearning.controller.professor;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("professor/")
public class ProfessorDashBoardController {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @GetMapping("dashboard")
    public String DashboardView(Model model) {
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
        } catch (SQLException sqlException) {
            model.addAttribute("course_list", null);
        }
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_PROFESSOR_DASHBOARD");
    }

    @GetMapping("create/course")
    public String CreateCourseView(Model model) {

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_COURSE");
    }

    @GetMapping("courseDetails")
    public String CourseDetailsView(@RequestParam String courseId, Model model) {
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateCourse("", "", "", 0);
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

        } catch (SQLException exception) {
            System.out.println("Course Module Fetch Error");
        }

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_MODULES_LIST");
    }

    @GetMapping("courseDetails/courseModuleDetails")
    public String CourseModuleDetailsView(@RequestParam String courseModuleId, @RequestParam String courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseModuleId", courseModuleId);

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateCourse("", "", "", 10);
        try {
            course = course.Load(courseId);
            String moduleName = course.GetCourseBase().GetModuleName(Integer.parseInt(courseModuleId));
            model.addAttribute("courseModuleName", moduleName);
        } catch (SQLException exception) {
            model.addAttribute("courseModuleName", "Error Loading Module");
        }

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_CONTENTS_LIST");
    }


    @PostMapping("courseDetails/AddModule")
    public RedirectView AddModuleView(@RequestParam String courseId, @RequestParam String course_module_name, RedirectAttributes redirectAttributes) {
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();
        CourseModule courseModule = courseContentFactory.CreateCourseModule(course_module_name);
        try {
            courseModule.Save(courseId);
            redirectAttributes.addFlashAttribute("message", "module created successfully");
        } catch (SQLException exception) {
            redirectAttributes.addFlashAttribute("message", "module creation failed");
        }

        return new RedirectView("?courseId=" + courseId);
    }

    @PostMapping("create/course")
    public String CourseSubmitView(@RequestParam String course_code, @RequestParam String course_name, @RequestParam String course_description,
                                   @RequestParam int total_credits, Model model) {
        try {
            ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
            Course course = courseFactory.CreateCourse(course_code, course_name, course_description, total_credits);
            course.Save();
            model.addAttribute("actionDone", true);
            model.addAttribute("actionName", "In Course Creation");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_ACTION_STATUS");
        } catch (SQLException exception) {
            model.addAttribute("actionDone", null);
            model.addAttribute("actionName", "In Course Creation");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_ACTION_STATUS");
        }
    }

    @GetMapping("createCourseByTerm")
    public String CreateCourseByTermView(@RequestParam String courseId, Model model) {
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        try {
            Course course = courseFactory.LoadCourseFromPersistence(courseId);
            UniversityTerms universityTerms = FactoryFacade.instance().getUniversityTermsFactory().createEmptyUniversityTermsInstance();
            ArrayList<IUniversityTerms> universityTermsArrayList = universityTerms.loadOpenForRegistrationTerms(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), new Date(System.currentTimeMillis()));
            model.addAttribute("universityTermsList", universityTermsArrayList);
            model.addAttribute("courseCode", course.GetCourseID());
            model.addAttribute("courseName", course.GetCourseName());
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_CREATE_COURSE_BY_TERM");
        } catch (SQLException exception) {
            model.addAttribute("actionDone", null);
            model.addAttribute("actionName", "In Course Load Creation");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_ACTION_STATUS");
        }
    }

    @PostMapping("createCourseByTerm/addCourseByTerm")
    public String CreateCourseByTermAddCourseByTerm(@RequestParam String courseId, @RequestParam String courseTerm,
                                                    @RequestParam String capacity, Model model) {
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        try {
            ICourse course = courseFactory.LoadCourseFromPersistence(courseId);
            course = course.Load(courseId);
            int capacityNumber = Integer.parseInt(capacity);

            IUniversityTerms universityTerms = FactoryFacade.instance().getUniversityTermsFactory().createUniversityTermsInstanceForLoadByID(courseTerm);
            universityTerms = universityTerms.loadTermByTermId(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), courseTerm);

            CourseByTerm courseByTerm = FactoryFacade.instance().getCourseFactory().createCourseByTermInstance(course, (UniversityTerms) universityTerms, capacityNumber);
            courseByTerm.save(CourseByTermPersistenceSingleton.GetMySqlCourseInstancePersistenceInstance());
            model.addAttribute("actionDone", true);
            model.addAttribute("actionName", "In Course Term Creation");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_ACTION_STATUS");
        } catch (SQLException exception) {
            model.addAttribute("actionDone", null);
            model.addAttribute("actionName", "In Course Term Creation");
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_COURSE_ACTION_STATUS");
        }
    }

}


