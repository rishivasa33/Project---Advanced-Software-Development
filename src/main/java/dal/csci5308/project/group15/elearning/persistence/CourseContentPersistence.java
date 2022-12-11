package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseContentPersistence {
    public int Save(CourseContent courseContent);

    public CourseContent Load(int courseContentId);

    public ArrayList<CourseContent> LoadAllContentsInModule(int courseModuleId) throws SQLException;
}
