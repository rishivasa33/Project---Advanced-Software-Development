package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.models.course.ICourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
@SessionAttributes({"student_number"})
public class StudentCourseController {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @GetMapping("/student/course/{courseInstanceID}")
    public String openCoursePage(@PathVariable String courseInstanceID, Model model) {
        try {
            ICourseByTerm courseByTerm = FactoryFacade.instance().getCourseFactory().LoadCourseByTermFromPersistence(courseInstanceID);
            ICourse course = courseByTerm.getCourseDetails();
            ArrayList<CourseModule> courseModuleArrayList = course.GetCourseBase().GetAllModules();
            ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
            for (CourseModule courseModule : courseModuleArrayList) {
                ArrayList<String> courseModuleDetails = new ArrayList<>();
                courseModuleDetails.add(courseModule.GetModuleName());
                courseModuleDetails.add(Integer.toString(courseModule.GetCourseModuleId()));
                arrayLists.add(courseModuleDetails);
            }
            System.out.println(arrayLists.size());
            model.addAttribute("courseId", course.GetCourseID());
            model.addAttribute("courseModules", arrayLists);
            model.addAttribute("courseInstanceID", courseInstanceID);
            model.addAttribute("success", true);
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_COURSE_PAGE");
        } catch (Exception exception) {
            model.addAttribute("success", false);
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_DASHBOARD");

        }
    }

    @GetMapping("student/course/courseModuleContent/{courseId}/{courseModuleId}")
    public String viewStudentDashboard(@PathVariable String courseId, @PathVariable String courseModuleId, Model model) {

        try {
            ICourse course = FactoryFacade.instance().getCourseFactory().LoadCourseFromPersistence(courseId);
            Integer courseModuleNumericId = Integer.parseInt(courseModuleId);
            ArrayList<CourseContent> courseContents = course.GetCourseBase().GetAllContentsInAModule(Integer.parseInt(courseModuleId));
            String courseModuleName = course.GetCourseBase().GetModuleName(courseModuleNumericId);
            ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
            for (CourseContent courseContent : courseContents) {
                ArrayList<String> courseContentDetails = new ArrayList<>();
                if (courseContent.IsTextContent()) {
                    courseContentDetails.add("TEXT");
                    courseContentDetails.add(courseContent.GetContentHeading());
                    TextCourseContent textCourseContent = (TextCourseContent) courseContent;
                    courseContentDetails.add(textCourseContent.GetTextContent());
                    courseContentDetails.add(Integer.toString(textCourseContent.GetContentId()));
                } else {
                    courseContentDetails.add("FILE");
                    courseContentDetails.add(courseContent.GetContentHeading());
                    FileCourseContent fileCourseContent = (FileCourseContent) courseContent;
                    courseContentDetails.add(fileCourseContent.GetFileName());
                    courseContentDetails.add(Integer.toString(fileCourseContent.GetContentId()));

                }
                arrayLists.add(courseContentDetails);
            }
            model.addAttribute("courseId", course.GetCourseID());
            model.addAttribute("courseModuleId", courseModuleId);
            model.addAttribute("courseModuleName", courseModuleName);
            model.addAttribute("courseContentList", arrayLists);
            model.addAttribute("success", true);

            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_COURSE_CONTENT_PAGE");

        } catch (Exception exception) {
            model.addAttribute("success", false);
            return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_COURSE_CONTENT_PAGE");
        }
    }

    @GetMapping("/student/viewRegisteredCoursesByTerm")
    public String fetchCurrentAndFutureTerms(Model model) {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createEmptyUniversityTermsInstance();

        ArrayList<IUniversityTerms> listOfAvailableTerms;
        listOfAvailableTerms = universityTerms.loadTermsAfterCurrentDate(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), new Date(System.currentTimeMillis()));

        model.addAttribute("terms_list", listOfAvailableTerms);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_REGISTERED_COURSES");
    }

    @GetMapping("/student/viewRegisteredCoursesByTerm/{termID}")
    public String viewRegisteredCoursesByTerm(@PathVariable String termID, Model model) {
        try {
            ArrayList<IStudentCourseEnrollment> enrolledCoursesByTerm;
            String studentNumber = String.valueOf(model.getAttribute("student_number"));
            IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
            IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);

            enrolledCoursesByTerm = studentCourseEnrollment.loadByTermAndStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentNumber, termID);

            model.addAttribute("enrolled_courses_by_term_list", enrolledCoursesByTerm);
            model.addAttribute("termID", termID);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_REGISTERED_COURSES_BY_TERM");
    }

    @GetMapping("/student/viewTermsOpenForRegistration")
    public String viewTermsOpenForRegistration(Model model) {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createEmptyUniversityTermsInstance();

        ArrayList<IUniversityTerms> listOfTermsOpenForRegistration;
        listOfTermsOpenForRegistration = universityTerms.loadOpenForRegistrationTerms(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), new Date(System.currentTimeMillis()));

        model.addAttribute("terms_open_for_registration_list", listOfTermsOpenForRegistration);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_TERMS_OPEN_FOR_REGISTRATION");
    }

    @GetMapping("/student/viewAvailableCoursesByTerm/{termID}")
    public String viewAvailableCoursesByTerm(@PathVariable String termID, Model model) {
        ArrayList<ICourseByTerm> availableCourses = null;
        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        ICourseByTerm courseByTerm = courseFactory.CreateCourseInstanceForLoadByTerm(termID);

        try {
            availableCourses = courseByTerm.loadByTerm(CourseByTermPersistenceSingleton.GetMySqlCourseInstancePersistenceInstance(), termID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("available_courses_by_term_list", availableCourses);
        model.addAttribute("termID", termID);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_REGISTER_NEW_COURSES_FOR_TERM");
    }

    @GetMapping("/student/registerForCourse")
    public String registerForCourse(@RequestParam("courseInstanceID") String courseInstanceID, @RequestParam("courseTerm") String courseTerm, @RequestParam("enrolledSeats") Integer enrolledSeats, @RequestParam("totalSeats") Integer totalSeats, Model model) {
        String registrationResult = "";
        String studentNumber = String.valueOf(model.getAttribute("student_number"));

        IStudentFactory courseFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = courseFactory.createStudentCourseEnrollmentInstanceForSave(courseInstanceID, studentNumber, courseTerm, enrolledSeats, totalSeats);

        try {
            registrationResult = studentCourseEnrollment.saveBasedOnCourseCount(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("registrationResult", registrationResult);
        model.addAttribute("courseInstanceID", courseInstanceID);
        model.addAttribute("studentNumber", studentNumber);
        model.addAttribute("courseTerm", courseTerm);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_REGISTER_NEW_COURSE_RESULT");
    }
}
