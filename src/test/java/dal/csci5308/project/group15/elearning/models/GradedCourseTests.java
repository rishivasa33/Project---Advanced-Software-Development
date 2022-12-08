package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class GradedCourseTests {


        @Test
    void TestGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        GradedCourse gradedCourse = courseFactory.CreateGradedCourse("5308", "test", "test2", 10);
        assertEquals(gradedCourse.GetCourse().GetName(), "test");
        assertEquals(gradedCourse.GetCourse().GetCourseID(), "5308");
        assertEquals(gradedCourse.GetCourse().GetDescription(), "test2");
        assertEquals(gradedCourse.GetCredits(), 10);
    }

    @Test
    void TestGradedCourseCredits(){
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse gradedCourse = courseFactory.CreateGradedCourse("5308", "test", "test2", 20);
        assertEquals(gradedCourse.GetCredits(), 20);
    }

    @Test
    void TestGradedCourseSave() throws SQLException {
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse course = courseFactory.CreateGradedCourse("5308", "test", "test2", 20);
        GradedCoursePersistence mockDBGradedCoursePersistence = GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        course.Save();
    }

    @Test
    void TestGradedCourseLoad() throws SQLException {

        GradedCoursePersistence coursePersistence =  GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        GradedCourse gradedCourse = coursePersistence.Load("5308");
        Assertions.assertEquals(gradedCourse.GetCourse().GetCourseID(), "5308");
        Assertions.assertEquals(gradedCourse.GetCourse().GetName(), "test5308");
        assertEquals(gradedCourse.GetCourse().GetDescription(), "test description");
        assertEquals(gradedCourse.GetCredits(), 10);
    }
}
