package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import java.sql.SQLException;

public interface CoursePersistence {

    BaseCourse Load(String course_id);

    int GenerateUniqueCourseID();

    void Save(BaseCourse baseCourse) throws SQLException;
}
