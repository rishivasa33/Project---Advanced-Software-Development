package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class StudentCourseEnrollmentTest {
    @Test
    void studentCourseEnrollmentCreationTest() {

        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstance("F22CSCI5100", "B00901111", "F22");

        assertEquals(studentCourseEnrollment.getCourseInstanceID(), "F22CSCI5100");
        assertEquals(studentCourseEnrollment.getStudentNumber(), "B00901111");
        assertEquals(studentCourseEnrollment.getCourseTerm(), "F22");
    }

    @Test
    void studentCourseEnrollmentCreationForLoadTest() {

        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad("B00901111");

        assertNull(studentCourseEnrollment.getCourseInstanceID());
        assertEquals(studentCourseEnrollment.getStudentNumber(), "B00901111");
        assertNull(studentCourseEnrollment.getCourseTerm());
    }

    @Test
    void saveBasedOnCourseCountTest() {
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstance("F22CSCI5100", "B00901111", "F22");
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();

        try {
            studentCourseEnrollmentPersistence.save((StudentCourseEnrollment) studentCourseEnrollment);
            Assertions.assertTrue(true);
        } catch (SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    void saveBasedOnCreditCountTest() {
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstance("F22CSCI5100", "B00901111", "F22");
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();

        try {
            studentCourseEnrollmentPersistence.save((StudentCourseEnrollment) studentCourseEnrollment);
            Assertions.assertTrue(true);
        } catch (SQLException e) {
            Assertions.fail();
        }
    }

    @Test
    void loadByStudentNumberTest() {
        String studentNumber = "B00901111";
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();
        try {
            studentCourseEnrollments = studentCourseEnrollment.loadByStudentNumber(studentCourseEnrollmentPersistence, studentNumber);
            assertEquals(studentCourseEnrollments.get(0).getCourseInstanceID(), "F22CSCI5308");
            assertEquals(studentCourseEnrollments.get(0).getCourseTerm(), "F22");
            assertEquals(studentCourseEnrollments.get(0).getStudentNumber(), studentNumber);
            assertEquals(studentCourseEnrollments.get(1).getCourseInstanceID(), "W23CSCI6308");
            assertEquals(studentCourseEnrollments.get(1).getStudentNumber(), studentNumber);
            assertEquals(studentCourseEnrollments.get(1).getCourseTerm(), "W23");
        } catch (SQLException e) {
            Assertions.fail();
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void loadByCourseInstanceIDTest() {
        String courseInstanceID = "F22CSCI5308";
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(courseInstanceID);
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();
        try {
            studentCourseEnrollments = studentCourseEnrollment.loadByCourseInstanceID(studentCourseEnrollmentPersistence, courseInstanceID);
            assertEquals(studentCourseEnrollments.get(0).getCourseInstanceID(), courseInstanceID);
            assertEquals(studentCourseEnrollments.get(0).getCourseTerm(), "F22");
            assertEquals(studentCourseEnrollments.get(0).getStudentNumber(), "B00909090");
            assertEquals(studentCourseEnrollments.get(1).getCourseInstanceID(), courseInstanceID);
            assertEquals(studentCourseEnrollments.get(1).getStudentNumber(), "B00812312");
            assertEquals(studentCourseEnrollments.get(1).getCourseTerm(), "F22");
        } catch (SQLException e) {
            Assertions.fail();
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void loadByTermAndStudentNumberTest() {
        String studentNumber = "B00901111";
        String term = "F22";
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();
        try {
            studentCourseEnrollments = studentCourseEnrollment.loadByTermAndStudentNumber(studentCourseEnrollmentPersistence, studentNumber, term);
            assertEquals(studentCourseEnrollments.get(0).getCourseInstanceID(), "F22CSCI5308");
            assertEquals(studentCourseEnrollments.get(0).getCourseTerm(), term);
            assertEquals(studentCourseEnrollments.get(0).getStudentNumber(), studentNumber);
        } catch (SQLException e) {
            Assertions.fail();
        } catch (ParseException e) {
            Assertions.fail();
        }
    }

    @Test
    void loadStudentCourseCountByTermTest() {
        String studentNumber = "B00901111";
        String term = "F22";
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();

        Integer courseCount = studentCourseEnrollmentPersistence.loadStudentCourseCountByTerm(studentNumber, term);
        assertEquals(courseCount, 3);
    }

    @Test
    void loadStudentCreditCountByTermTest() {
        String studentNumber = "B00901111";
        String term = "F22";
        IStudentFactory studentCourseEnrollmentFactory = FactoryFacade.instance().getStudentFactory();
        IStudentCourseEnrollment studentCourseEnrollment = studentCourseEnrollmentFactory.createStudentCourseEnrollmentInstanceForLoad(studentNumber);
        IStudentCourseEnrollmentPersistence studentCourseEnrollmentPersistence = StudentCourseEnrollmentPersistenceSingleton.GetMockDBStudentCourseEnrollmentPersistenceInstance();

        Integer creditsCount = studentCourseEnrollmentPersistence.loadStudentCreditCountByTerm(studentNumber, term);
        assertEquals(creditsCount, 9);
    }
}
