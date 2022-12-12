package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;
import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UngradedCourseTests {

    @Test
    void TestUnGradedCourseLoad(){
        UnGradedCoursePersistence unGradedCoursePersistence =  UnGradedCoursePersistenceSingleton.GetMockDBUnGradedCoursePersistenceInstance();
        UnGradedCourse ungradedCourse = unGradedCoursePersistence.Load("5308");
        Assertions.assertEquals(ungradedCourse.GetCourseBase().GetName() , "test5308");
        Assertions.assertEquals(ungradedCourse.GetCourseBase().GetCourseID(), "5308");

    }

    @Test
    void TestUnGradedCourseSave() throws SQLException {

        CourseFactory courseFactory = new CourseFactory();
        UnGradedCourse course = courseFactory.CreateUngradedCourse("5308", "test", "test2");
        UnGradedCoursePersistence coursePersistence = UnGradedCoursePersistenceSingleton.GetMockDBUnGradedCoursePersistenceInstance();
        course.Save();
    }

    @Test
    void TestUnGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        UnGradedCourse course = courseFactory.CreateUngradedCourse("5308", "test", "test2");
    }






}
