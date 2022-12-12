package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TextCourseContentPersistence{
    public int Save(TextCourseContent textCourseContent, int courseModuleId) throws SQLException;

    public TextCourseContent Load(int textCourseContentId) throws SQLException;

    public ArrayList<CourseContent> LoadAllContentsInModule(int courseModuleId) throws SQLException;
}
