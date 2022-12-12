package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TextCourseContentTest {


        @Test
        void TestTextCourseContentCreation(){

            CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
            TextCourseContent textCourseContent = courseContentFactoryFactory.CreateTextCourseContent("content1", "text");
            assertEquals(textCourseContent.GetContentHeading(), "content1");
            assertEquals(textCourseContent.GetTextContent(), "text");

        }

    @Test
    void TestTextCourseContentSave() throws SQLException {

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        TextCourseContent textCourseContent = courseContentFactoryFactory.CreateTextCourseContent("content1", "text");
        textCourseContent.Save(1);
        assertEquals(textCourseContent.GetContentId(), 1);

    }



    @Test
    void TestTextCourseContentLoad() throws SQLException {

        CourseContentFactory courseContentFactoryFactory = new CourseContentFactory();
        TextCourseContent textCourseContent = courseContentFactoryFactory.CreateTextCourseContent("content1", "text");
        textCourseContent = (TextCourseContent) textCourseContent.Load(1);
        assertEquals(textCourseContent.GetContentHeading(), "content heading");
        assertEquals(textCourseContent.GetTextContent(), "content text");
        assertEquals(textCourseContent.GetContentId(), 1);
    }
}
