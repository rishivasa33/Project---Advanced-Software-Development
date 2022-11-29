package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.GradedCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GradedCoursePersistence {

    void Save(GradedCourse gradedCourse) throws SQLException;

    GradedCourse Load(String course_id) throws SQLException;

    ArrayList<GradedCourse> GetAllGradedCourses() throws SQLException;
}
