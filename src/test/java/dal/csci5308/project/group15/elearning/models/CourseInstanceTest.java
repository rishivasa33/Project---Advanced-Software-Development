package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.CourseInstance;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.persistence.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseInstanceTest {

    @Test
    void TestCourseInstanceCreation() {

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/09/2022";
        String end_date = "20/12/2022";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            Assertions.assertTrue(true);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceCreationInvalidStartDate(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/09/202233";
        String end_date = "20/12/2022";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            Assertions.assertTrue(true);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceCreationInvalidEndDate(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/09/2022";
        String end_date = "20/12/2022323";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            Assertions.assertTrue(true);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceGetCourseNameFall(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/09/2022";
        String end_date = "20/12/2022323";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            String course_instance_name = courseInstance.GetName();
            String expected_name = "2022 Fall test";
            assertEquals(course_instance_name, expected_name);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceGetCourseNameSummer(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/05/2022";
        String end_date = "20/08/2022";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            String course_instance_name = courseInstance.GetName();
            String expected_name = "2022 Summer test";
            assertEquals(course_instance_name, expected_name);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceGetCourseNameWinter(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("Test", "test", "test2", 10);
        String start_date = "05/01/2022";
        String end_date = "20/04/2022";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            String course_instance_name = courseInstance.GetName();
            String expected_name = "2022 Winter test";
            assertEquals(course_instance_name, expected_name);
        }
        catch (ParseException exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceSave(){

        CourseFactory courseFactory = new CourseFactory();
        ICourse course = courseFactory.CreateGradedCourse("test", "test", "test2", 10);
        String start_date = "05/01/2022";
        String end_date = "20/04/2022";
        try{
            CourseInstance courseInstance =  courseFactory.CreateCourseInstance(course,start_date,end_date);
            String course_instance_name = courseInstance.GetName();
            String expected_name = "2022 Winter test";
            assertEquals(course_instance_name, expected_name);
            CourseInstancePersistence courseInstancePersistence = CourseInstancePersistenceSingleton.GetMockDBCourseInstancePersistenceInstance();
            courseInstance.Save(courseInstancePersistence);
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }

    @Test
    void TestCourseInstanceLoad(){


        try{
            CourseInstancePersistence courseInstancePersistence =  CourseInstancePersistenceSingleton.GetMockDBCourseInstancePersistenceInstance();
            String test_course_id = "test";
            CourseInstance courseInstance =  courseInstancePersistence.Load(test_course_id);
            assertEquals(courseInstance.GetCourse().GetCourseID(), test_course_id);
            assertEquals(courseInstance.GetName(), "2023 Winter test");
        }
        catch (Exception exception){
            Assertions.fail();
        }
    }

}
