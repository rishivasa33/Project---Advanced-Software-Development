package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestStudentCourseEnrollment {
    @Test
    void TestStudentCourseEnrollmentCreation() {

        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstance("F22CSCI5100", "B00901111", "F22");

        assertEquals(studentCourseEnrollment.getCourseInstanceID(), "F22CSCI5100");
        assertEquals(studentCourseEnrollment.getStudentNumber(), "B00901111");
        assertEquals(studentCourseEnrollment.getCourseTerm(), "F22");
    }

    @Test
    void TestStudentCourseEnrollmentCreationForLoad() {

        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad("B00901111");

        assertEquals(studentCourseEnrollment.getCourseInstanceID(), null);
        assertEquals(studentCourseEnrollment.getStudentNumber(), "B00901111");
        assertEquals(studentCourseEnrollment.getCourseTerm(), null);
    }


    @Test
    void TestStudentCourseEnrollmentSave() {

        /*IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentCourseEnrollmentFactory();
        */
    }

    @Test
    void TestStudentCourseEnrollmentLoadByTermAndStudentNumber() {
        /*try {
            String studentNumber = "B00901111";
            String currentTerm = "F22";
            IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentCourseEnrollmentFactory();
            IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);

            ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
            studentCourseEnrollments = studentCourseEnrollment.loadByTermAndStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMockDBGradedCoursePersistenceInstance(), studentNumber, currentTerm);

            assertEquals(studentCourseEnrollments.get(0).getCourseInstanceID(), test_course_id);

        } catch (Exception exception) {
            Assertions.fail();
        }*/
    }


}
