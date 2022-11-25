package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;

import java.sql.SQLException;

public interface UnGradedCoursePersistence {

    UnGradedCourse Load(int course_id);

    void Save(UnGradedCourse course) throws SQLException;
}
