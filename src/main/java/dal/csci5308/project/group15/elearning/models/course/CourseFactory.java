package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistenceSingleton;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

public class CourseFactory implements ICourseFactory {
    public Course CreateCourse(String course_id, String course_name, String course_description, int total_credits){
        return new Course(course_id, course_name, course_description, total_credits);
    }

    public UnGradedCourse CreateUngradedCourse(String course_id, String course_name, String course_description){
        return new UnGradedCourse(course_id, course_name, course_description);
    }

    public BaseCourse CreateBaseCourse(String course_id, String course_name, String course_description){
        return new BaseCourse(course_id, course_name, course_description);
    }

    public CourseByTerm CreateCourseInstance(String courseInstanceID, ICourse course, String start_date, String end_date, String courseTerm, Integer enrolledSeats, Integer totalSeats) throws ParseException {
        return new CourseByTerm(courseInstanceID, course, start_date, end_date, courseTerm, enrolledSeats, totalSeats);
    }

    public CourseByTerm CreateCourseInstance(String courseInstanceID, ICourse course, Date start_date, Date end_date, String courseTerm, Integer enrolledSeats, Integer totalSeats) throws ParseException {
        return new CourseByTerm(courseInstanceID, course, start_date, end_date, courseTerm, enrolledSeats, totalSeats);
    }

    public Course LoadCourseFromPersistence(String courseID) throws SQLException {

        Course course = new Course(courseID);
        course = course.Load(courseID);
        return course;
    }



    public CourseByTerm createCourseByTermInstance(ICourse course, UniversityTerms terms, int capacity){
        return new CourseByTerm(course, terms, capacity);
    }

    @Override
    public CourseByTerm CreateCourseInstanceForLoadByTerm(String term){
        CourseByTerm  courseByTerm = new CourseByTerm(term);
        return courseByTerm;
    }

    public ICourseByTerm LoadCourseByTermFromPersistence(String courseByTermId) throws SQLException, ParseException {
        CourseByTerm courseByTerm = new CourseByTerm("");
        return courseByTerm.loadByID(CourseByTermPersistenceSingleton.GetCourseByTermPersistence(), courseByTermId);
    }

}
