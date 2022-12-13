package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.TextCourseContentPersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockDBTextCourseContentPersistence implements TextCourseContentPersistence{

    public int Save(TextCourseContent courseContent, int courseModuleId){
       return 1;
    }

    public TextCourseContent Load(int courseContentId){
        CourseContentFactory courseContentFactory = new CourseContentFactory();
        return courseContentFactory.CreateTextCourseContent ("content heading", "content text");
    }

    public ArrayList<CourseContent> LoadAllContentsInModule(int courseModuleId) throws SQLException {
        return null;
    }
}
