package dal.csci5308.project.group15.elearning.controller.student;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.authUser.AuthUserUserFactory;
import dal.csci5308.project.group15.elearning.factory.authUser.IAuthUserFactory;
import dal.csci5308.project.group15.elearning.factory.notification.CourseMaterialDeadlineNotificationFactory;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.deadlineNotification.CourseMaterialDeadlineNotification;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.student.StudentDetailsSingleton;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import dal.csci5308.project.group15.elearning.security.authUser.IAuthUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"student_number"})
public class StudentDashboardController {
    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @GetMapping("student/dashboard")
    public String viewStudentDashboard(Model model) {

        String studentNumber = fetchCurrentStudentDetails().getStudentNumber();
        String currentTerm = fetchCurrentTerm().getTermID();
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = getCurrentEnrolledCourses(studentNumber, currentTerm);
        model.addAttribute("student_current_courses", studentCourseEnrollments);
        model.addAttribute("student_number", studentNumber);
        model.addAttribute("current_term", currentTerm);

        List<CourseMaterialDeadlineNotification> notificationList = fetchUpcomingDeadlines();
        model.addAttribute("deadlines", notificationList);

        return propertiesFactory.makeRedirectionsProperties().getPropertiesMap().get("TEMPLATE_STUDENT_DASHBOARD");
    }


    private IStudentDetails fetchCurrentStudentDetails() {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentDetails studentDetails = studentFactory.createEmptyStudentDetailsInstance();
        IAuthUser authUser = AuthUserUserFactory.instance().makeAuthUser();
        studentDetails = studentDetails.loadByUserName(StudentDetailsSingleton.GetMySqlStudentDetailsInstance(), authUser.getUsername());

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
        CourseMaterialDeadlineNotification courseMaterialDeadlineNotification = CourseMaterialDeadlineNotificationFactory.instance().makeCourseMaterialDeadlineNotification();
        IDatabaseOperations databaseOperations = DatabaseOperations.instance();
        IAuthUserFactory authFactory = AuthUserUserFactory.instance();
        IAuthUser authUser = authFactory.makeAuthUser();

        return courseMaterialDeadlineNotification.getCourseMaterialDeadlineNotifications(databaseOperations, authUser);
    }


}
