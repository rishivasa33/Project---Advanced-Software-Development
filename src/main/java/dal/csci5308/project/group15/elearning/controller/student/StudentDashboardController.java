package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollmentFactory;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class StudentDashboardController {

    //This method renders all the elements when a student logs in and views their Dashboard
    @GetMapping("student/dashboard")
    public String viewStudentDashboard(Model model) {
        //TODO: Update hardcoded values to fetch from SessionContext
        //TODO: Fetch Course details from CourseInstanceID
        //Fetch Student's currently enrolled courses
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = getCurrentEnrolledCourses("B00901111", "F22");
        model.addAttribute("student_current_courses", studentCourseEnrollments);

        //Fetch Student's university-level announcements

        //Fetch Student's Calendar/Upcoming Deadlines
        return "studentDashboard";
    }

    private ArrayList<IStudentCourseEnrollment> getCurrentEnrolledCourses(String studentNumber, String currentTerm) {
        try {
            IStudentCourseEnrollmentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentCourseEnrollmentFactory();
            IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad("B00901111");

            return studentCourseEnrollment.loadByTermAndStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentNumber, currentTerm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
