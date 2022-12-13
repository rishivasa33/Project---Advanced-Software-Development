package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.deadlineNotification.CourseMaterialDeadlineNotification;
import dal.csci5308.project.group15.elearning.deadlineNotification.ICourseMaterialDeadlineNotificationHandler;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.notification.CourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.student.StudentDetailsSingleton;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import dal.csci5308.project.group15.elearning.security.AuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentDashboardController {

    @GetMapping("student/dashboard")
    public String viewStudentDashboard(Model model) {

        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = getCurrentEnrolledCourses(fetchCurrentStudentDetails().getStudentNumber(), fetchCurrentTerm().getTermID());
        model.addAttribute("student_current_courses", studentCourseEnrollments);

        //Fetch Student's university-level announcements

        List<CourseMaterialDeadlineNotification> notificationList = fetchUpcomingDeadlines();
        model.addAttribute("deadlines", notificationList);

        return "studentDashboard";
    }

    private IStudentDetails fetchCurrentStudentDetails() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createEmptyStudentDetailsInstance();
        studentDetails = studentDetails.loadByUserName(StudentDetailsSingleton.GetMySqlStudentDetailsInstance(), AuthUser.getUsername());

        return studentDetails;
    }

    private IUniversityTerms fetchCurrentTerm() {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms currentTerm = universityTermsFactory.createEmptyUniversityTermsInstance();

        currentTerm = currentTerm.loadCurrentTerm(UniversityTermsSingleton.GetMySqlUniversityTermsPersistenceInstance(), new Date(System.currentTimeMillis()));

        return currentTerm;
    }

    private ArrayList<IStudentCourseEnrollment> getCurrentEnrolledCourses(String studentNumber, String currentTerm) {
        try {
            IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
            IStudentCourseEnrollment studentCourseEnrollment = studentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);

            return studentCourseEnrollment.loadByTermAndStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentNumber, currentTerm);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CourseMaterialDeadlineNotification> fetchUpcomingDeadlines() {
        ICourseMaterialDeadlineNotificationHandler courseMaterialDeadlineNotificationHandler = CourseMaterialDeadlineNotificationFactory.instance().makeCourseMaterialDeadlineNotificationHandler();
        return courseMaterialDeadlineNotificationHandler.getCourseMaterialDeadlineNotifications();
    }


}
