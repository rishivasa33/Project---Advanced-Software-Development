package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.models.course.CourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseByTerm;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface CourseInstancePersistence {


    void save(CourseByTerm courseByTerm) throws SQLException;

    ICourseByTerm loadByID(String courseId) throws ParseException, SQLException;

    ArrayList<ICourseByTerm> loadByTerm(String courseTerm) throws ParseException, SQLException;
}
