package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class CourseTests {


        @Test
    void TestGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateCourse("5308", "test", "test2", 10);
        assertEquals(course.GetCourseBase().GetName(), "test");
        assertEquals(course.GetCourseBase().GetCourseID(), "5308");
        assertEquals(course.GetCourseBase().GetDescription(), "test2");
        assertEquals(course.GetCredits(), 10);
    }

    @Test
    void TestGradedCourseCredits(){
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateCourse("5308", "test", "test2", 20);
        assertEquals(course.GetCredits(), 20);
    }

    @Test
    void TestGradedCourseSave() throws SQLException {
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateCourse("5308", "test", "test2", 20);
        GradedCoursePersistence mockDBGradedCoursePersistence = GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        course.Save();
    }

    @Test
    void TestGradedCourseLoad() throws SQLException {

        GradedCoursePersistence coursePersistence =  GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        Course course = coursePersistence.Load("5308");
        Assertions.assertEquals(course.GetCourseBase().GetCourseID(), "5308");
        Assertions.assertEquals(course.GetCourseBase().GetName(), "test5308");
        assertEquals(course.GetCourseBase().GetDescription(), "test description");
        assertEquals(course.GetCredits(), 10);
    }
}
