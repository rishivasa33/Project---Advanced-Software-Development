package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistenceSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;
import static  org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest

public class GradedCourseTests {


        @Test
    void TestGradedCourseCreation(){

        CourseFactory courseFactory = new CourseFactory();
        GradedCourse gradedCourse = courseFactory.CreateGradedCourse(1, "test", "test2", 10);
        assertEquals(gradedCourse.GetCourse().GetName(), "test");
        assertEquals(gradedCourse.GetCourse().GetCourseID(), 1);
        assertEquals(gradedCourse.GetCourse().GetDescription(), "test2");
        assertEquals(gradedCourse.GetCredits(), 10);
    }

    @Test
    void TestGradedCourseCredits(){
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse gradedCourse = courseFactory.CreateGradedCourse(1, "test", "test2", 20);
        assertEquals(gradedCourse.GetCredits(), 20);
    }

    @Test
    void TestGradedCourseSave(){
        CourseFactory courseFactory = new CourseFactory();
        GradedCourse course = courseFactory.CreateGradedCourse(1, "test", "test2", 20);
        GradedCoursePersistence mockDBGradedCoursePersistence = GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        course.Save(mockDBGradedCoursePersistence);
    }

    @Test
    void TestGradedCourseLoad(){

        GradedCoursePersistence coursePersistence =  GradedCoursePersistenceSingleton.GetMockDBGradedCoursePersistenceInstance();
        GradedCourse gradedCourse = coursePersistence.Load(1);
        Assertions.assertEquals(gradedCourse.GetCourse().GetCourseID(), 1);
        Assertions.assertEquals(gradedCourse.GetCourse().GetName(), "test1");
        assertEquals(gradedCourse.GetCourse().GetDescription(), "test description");
        assertEquals(gradedCourse.GetCredits(), 10);
    }

    //db check.

//    @Test
//    void TestGradedCourseSave2(){
//        Database database = new Database();
//        CourseFactory courseFactory = new CourseFactory();
//        GradedCourse course = courseFactory.CreateGradedCourse(1, "test", "test2", 20);
//        MySqlCoursePersistence coursePersistence = new MySqlCoursePersistence(database);
//        GradedCoursePersistence gradedCoursePersistence = new MySqlGradedCoursePersistence(coursePersistence,database);
//        course.Save(gradedCoursePersistence);
//    }
//
//    @Test
//    void TestGradedCourseLoad2(){
//        Database database = new Database();
//        CourseFactory courseFactory = new CourseFactory();
//        MySqlCoursePersistence coursePersistence = new MySqlCoursePersistence(database);
//        GradedCoursePersistence  graded_coursePersistence = new MySqlGradedCoursePersistence(coursePersistence,database);
//        GradedCourse gradedCourse = courseFactory.LoadGradedCourse(1, graded_coursePersistence);
//    }
}
