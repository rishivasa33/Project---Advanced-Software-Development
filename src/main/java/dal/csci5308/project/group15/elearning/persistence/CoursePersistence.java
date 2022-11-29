package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.Course;

import java.sql.SQLException;


public interface CoursePersistence {

    Course Load(String course_id);

    int GenerateUniqueCourseID();

    void Save(Course course) throws SQLException;
}
