package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
@SessionAttributes({"student_number"})
public class StudentCourseController {

    @GetMapping("/student/course/{courseInstanceID}")
    public String openCoursePage(@PathVariable String courseInstanceID, Model model) {
        System.out.println("Opening Course Page: " + courseInstanceID);
        model.addAttribute("courseInstanceID", courseInstanceID);
        return "studentCoursePage";
    }

    @GetMapping("/student/viewRegisteredCoursesByTerm")
    public String fetchCurrentAndFutureTerms(Model model) {
        //Fetches Terms where Term end date is AFTER Current System Date
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createEmptyUniversityTermsInstance();

        ArrayList<IUniversityTerms> listOfAvailableTerms;
        listOfAvailableTerms = universityTerms.loadTermsAfterCurrentDate(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), new Date(System.currentTimeMillis()));

        model.addAttribute("terms_list", listOfAvailableTerms);

        return "studentRegisteredCourses";
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
        return "studentRegisteredCoursesByTerm";
    }

}
