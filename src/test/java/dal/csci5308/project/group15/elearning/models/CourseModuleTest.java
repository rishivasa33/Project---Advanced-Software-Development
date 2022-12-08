package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
    void TestTextCourseModuleSave(){

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactoryFactory.CreateCourseModule("module1");
        courseModule.Save();
        assertEquals(courseModule.GetModuleName(), "module1");
        assertEquals(courseModule.GetCourseModuleId(), 1);

    }



    @Test
    void TestTextCourseModuleLoad(){

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        CourseModule courseModule = courseContentFactoryFactory.CreateCourseModule("empty");
        courseModule = courseModule.Load(1);
        assertEquals(courseModule.GetModuleName(), "module1");
        assertEquals(courseModule.GetCourseModuleId(), 1);

    }
}
