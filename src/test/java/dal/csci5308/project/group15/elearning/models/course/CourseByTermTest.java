package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistence;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseByTermTest {
    @Test
    void TestCourseInstanceCreation() {

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateCourse("test", "test", "test2", 10);
        String start_date = "05/09/2022";
        String end_date = "20/12/2022";

        try {
            CourseByTerm courseByTerm = courseFactory.CreateCourseInstance("test", course, "12/05/2022", "20/05/2022", "testTerm", 20, 40);
            Assertions.assertTrue(true);
        } catch (ParseException exception) {
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceCreationInvalidStartDate() {

        ICourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateCourse("test", "test", "test2", 10);
        String start_date = "05/09a/202233";
        String end_date = "20/12/2022";
        try {
            CourseByTerm courseByTerm = courseFactory.CreateCourseInstance("test", course, start_date, "20/05/2022", "testTerm", 20, 40);
            Assertions.fail();
        } catch (ParseException exception) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    void TestCourseInstanceCreationInvalidEndDate() {

        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        ICourse course = courseFactory.CreateCourse("test", "test", "test2", 10);
        String start_date = "05/09/2022";
        String end_date = "20/12/b2022323";
        try {
            CourseByTerm courseByTerm = courseFactory.CreateCourseInstance("test", course, start_date, end_date, "testTerm", 20, 40);
            Assertions.assertTrue(false);
        } catch (ParseException exception) {
            Assertions.assertTrue(true);
        }
    }


    @Test
    void TestCourseInstanceSave() {

        ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
        ICourse course = courseFactory.CreateCourse("test", "test", "test2", 10);
        String start_date = "05/01/2022";
        String end_date = "20/04/2022";
        try {
            CourseByTerm courseByTerm = courseFactory.CreateCourseInstance("test", course, "12/05/2022", "20/05/2022", "testTerm", 20, 40);
            CourseByTermPersistence courseInstancePersistence = CourseByTermPersistenceSingleton.GetMockDBCourseInstancePersistenceInstance();
            courseByTerm.save(courseInstancePersistence);
            Assertions.assertTrue(true);
        } catch (Exception exception) {
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceLoadByTerm() {


        try {
            CourseByTermPersistence courseInstancePersistence = CourseByTermPersistenceSingleton.GetMockDBCourseInstancePersistenceInstance();
            String test_course_instance_term = "F22";
            ArrayList<ICourseByTerm> courseByTerm = courseInstancePersistence.loadByTerm(test_course_instance_term);
            assertEquals(courseByTerm.get(0).getCourseDetails().GetCourseBase().GetCourseID(), "CSCI5100");
            assertEquals(courseByTerm.get(0).getCourseInstanceID(), "TestCInsID1");
            assertEquals(courseByTerm.get(0).getCourseTerm(), "F22");
            assertEquals(courseByTerm.get(0).getEnrolledSeats(), 10);
            assertEquals(courseByTerm.get(0).getTotalSeats(), 20);
        } catch (Exception exception) {
            Assertions.fail();
        }

    }

    void TestCourseInstanceLoadById() {


        try {
            CourseByTermPersistence courseInstancePersistence = CourseByTermPersistenceSingleton.GetMockDBCourseInstancePersistenceInstance();
            String test_course_instance_id = "CSCI5100";
            ICourseByTerm courseByTerm = courseInstancePersistence.loadByID(test_course_instance_id);
            assertEquals(courseByTerm.getCourseDetails().GetCourseBase().GetCourseID(), "CSCI5100");
            assertEquals(courseByTerm.getCourseInstanceID(), test_course_instance_id);
            assertEquals(courseByTerm.getCourseTerm(), "F22");

        } catch (Exception exception) {
            Assertions.fail();
        }

    }

}
