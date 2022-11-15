package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class CourseTests {


    @Test
    void TestGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.CreateGradedCourse(1, "test", 20);
        assertEquals(course.GetName(), "test");
    }

    @Test
    void TestGradedCourseCredits(){
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse course = courseFactory.CreateGradedCourse(1, "test", 20);
        assertEquals(course.GetCredits(), 20);
    }

    @Test
    void TestGradedCourseSave(){
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse course = courseFactory.CreateGradedCourse(1, "test", 20);
        CoursePersistence coursePersistence = new MockDBCoursePersistence();
        course.Save(coursePersistence);
    }

    @Test
    void TestGradedCourseLoad(){
        CourseFactory courseFactory = new CourseFactory();
        CoursePersistence coursePersistence = new MockDBCoursePersistence();
        GradedCourse gradedCourse = courseFactory.LoadGradedCourse(1, coursePersistence);
    }
    @Test
    void TestUnGradedCourseLoad(){
        CourseFactory courseFactory = new CourseFactory();
        CoursePersistence coursePersistence = new MockDBCoursePersistence();
        UngradedCourse ungradedCourse = courseFactory.LoadUnGradedCourse(2, coursePersistence);
    }

    @Test
    void TestUnGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        UngradedCourse course = courseFactory.CreateUngradedCourse(1, "test");
        CoursePersistence coursePersistence = new MockDBCoursePersistence();
        course.Save(coursePersistence);
    }






}
