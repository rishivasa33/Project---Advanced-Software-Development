package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.ICourse;

import java.sql.SQLException;

public interface ICoursePersistence {

    void Save(ICourse iCourse) throws SQLException;

    ICourse Load(ICourse iCourse, int iCourseId);
}
