package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.models.course.UnGradedCourse;

import java.sql.SQLException;

public interface UnGradedCoursePersistence{

    UnGradedCourse Load(String course_id);

    void Save(UnGradedCourse course) throws SQLException;
}
