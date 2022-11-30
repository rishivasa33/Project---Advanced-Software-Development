package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.CourseInstance;

import java.sql.SQLException;
import java.text.ParseException;

public interface CourseInstancePersistence {


    void Save(CourseInstance courseInstance) throws SQLException;

    CourseInstance Load(String courseId) throws ParseException, SQLException;


}
