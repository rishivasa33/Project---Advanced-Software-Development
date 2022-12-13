package dal.csci5308.project.group15.elearning.models.course;

import java.text.ParseException;
import java.util.Date;

public interface ICourseFactory {

    Course CreateGradedCourse(String course_id, String course_name, String course_description, int total_credits);

    UnGradedCourse CreateUngradedCourse(String course_id, String course_name, String course_description);

    BaseCourse CreateCourse(String course_id, String course_name, String course_description);

    CourseByTerm CreateCourseInstance(String courseInstanceID, ICourse course, String start_date, String end_date, String courseTerm, Integer enrolledSeats, Integer totalSeats) throws ParseException;

    CourseByTerm CreateCourseInstance(String courseInstanceID, ICourse course, Date start_date, Date end_date, String courseTerm, Integer enrolledSeats, Integer totalSeats) throws ParseException;

    CourseByTerm CreateCourseInstanceForLoadByTerm(String term);

    Course createCourseInstanceForLoad(String courseID);
}
