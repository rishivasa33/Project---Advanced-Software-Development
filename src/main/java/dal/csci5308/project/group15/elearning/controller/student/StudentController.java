package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.factory.FactoryInitializer;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollmentFactory;
import dal.csci5308.project.group15.elearning.persistence.StudentCourseEnrollmentPersistenceSingleton;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@Controller
public class StudentController {

    //This method renders all the elements when a student logs in and views their Dashboard
    @GetMapping("student/dashboard")
    public String studentDashboard(Model model) {
        //TODO: Update hardcoded values to fetch from SessionContext
        //Fetch Student's currently enrolled courses
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = getCurrentEnrolledCourses("B00901111", "F22");
        model.addAttribute("student_current_courses", studentCourseEnrollments);

        //Fetch Student's university-level announcements

        //Fetch Student's Calendar/Upcoming Deadlines
        return "studentDashboard";
    }

    @GetMapping("/course/{courseInstanceID}")
    public String openCoursePage(@PathVariable String courseInstanceID, Model model) {
        System.out.println("Opening Course Page: " + courseInstanceID);
        model.addAttribute("courseInstanceID", courseInstanceID);
        return "studentCoursePage";
    }

    private ArrayList<IStudentCourseEnrollment> getCurrentEnrolledCourses(String studentNumber, String currentTerm) {
        try {
            IStudentCourseEnrollmentFactory studentCourseEnrollmentFactory = FactoryInitializer.instance().getStudentCourseEnrollmentFactory();
            IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad("B00901111");

            return studentCourseEnrollment.loadByTermAndStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentNumber, currentTerm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
