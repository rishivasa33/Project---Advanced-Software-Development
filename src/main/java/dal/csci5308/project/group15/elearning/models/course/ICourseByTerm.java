package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface ICourseByTerm {

    String getCourseInstanceID();

    ICourse getCourseDetails();

    Date getCourseStartDate();

    Date getCourseEndDate();

    String getCourseTerm();

    Integer getEnrolledSeats();

    Integer getTotalSeats();

    void save(CourseByTermPersistence courseInstancePersistence) throws SQLException;

    ICourseByTerm loadByID(CourseByTermPersistence courseInstancePersistence, String course_instance_id) throws SQLException, ParseException;

    ArrayList<ICourseByTerm> loadByTerm(CourseByTermPersistence courseInstancePersistence, String course_term) throws SQLException, ParseException;

}
