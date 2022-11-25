package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBUngradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;
import dal.csci5308.project.group15.elearning.models.course.UngradedCourse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UngradedCourseTests {

    @Test
    void TestUnGradedCourseLoad(){
        CourseFactory courseFactory = new CourseFactory();
        UnGradedCoursePersistence unGradedCoursePersistence = new MockDBUngradedCoursePersistence();
        UngradedCourse ungradedCourse = courseFactory.LoadUnGradedCourse(2, unGradedCoursePersistence);
        Assertions.assertEquals(ungradedCourse.GetCourse().GetName() , "test2");
        Assertions.assertEquals(ungradedCourse.GetCourse().GetCourseID(), 2);

    }

    @Test
    void TestUnGradedCourseSave(){

        CourseFactory courseFactory = new CourseFactory();
        UngradedCourse course = courseFactory.CreateUngradedCourse(1, "test", "test2");
        UnGradedCoursePersistence coursePersistence = new MockDBUngradedCoursePersistence();
        course.Save(coursePersistence);
    }

    @Test
    void TestUnGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        UngradedCourse course = courseFactory.CreateUngradedCourse(1, "test", "test2");
    }






}
