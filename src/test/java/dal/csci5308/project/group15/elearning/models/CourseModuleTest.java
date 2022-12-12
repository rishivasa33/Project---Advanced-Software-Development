package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.persistence.CourseModulePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCourseModulePersistence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseModuleTest {

    @Test
    void TestTextCourseModuleCreation(){

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactoryFactory.CreateCourseModule("module1");
        assertEquals(courseModule.GetModuleName(), "module1");


    }

    @Test
    void TestTextCourseModuleSave() throws SQLException {

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactoryFactory.CreateCourseModule("module1");
        String course_code_id = "CSCI 5000";
        courseModule.Save(course_code_id);
        assertEquals(courseModule.GetModuleName(), "module1");
        assertEquals(courseModule.GetCourseModuleId(), 1);

    }



    @Test
    void TestTextCourseModuleLoad() throws SQLException {

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactoryFactory.CreateCourseModule("empty");
        courseModule = courseModule.Load(1);
        assertEquals(courseModule.GetModuleName(), "module1");
        assertEquals(courseModule.GetCourseModuleId(), 1);

    }

    @Test
    void TestTextCourseModuleLoadMysql() throws SQLException {

        CourseModulePersistence courseModulePersistence = new MySqlCourseModulePersistence();
        CourseModule courseModule = courseModulePersistence.Load(1);

    }
}
