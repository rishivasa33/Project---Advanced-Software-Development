package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseByTermPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseByTerm implements ICourseByTerm {

    private String courseInstanceID;
    private ICourse courseDetails;
    private Date courseStartDate;
    private Date courseEndDate;
    private String courseTerm;
    private Integer enrolledSeats;
    private Integer totalSeats;

    CourseByTerm(String courseInstanceID, ICourse courseDetails, Date courseStartDate, Date courseEndDate, String courseTerm, Integer enrolledSeats, Integer totalSeats) {
        this.courseInstanceID = courseInstanceID;
        this.courseDetails = courseDetails;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseTerm = courseTerm;
        this.enrolledSeats = enrolledSeats;
        this.totalSeats = totalSeats;
    }

    CourseByTerm(ICourse course, UniversityTerms term, Integer totalSeats) {
        this.courseInstanceID = term.getTermID() + course.GetCourseID();
        this.courseInstanceID = this.courseInstanceID.replaceAll(" ", "");
        this.courseStartDate = term.getTermStartDate();
        this.courseEndDate = term.getTermEndDate();
        this.courseTerm = term.getTermID();
        this.courseDetails = course;
        this.enrolledSeats = 0;
        this.totalSeats = totalSeats;
    }

    CourseByTerm(String courseInstanceID, ICourse courseDetails, String courseStartDate, String courseEndDate, String courseTerm, Integer enrolledSeats, Integer totalSeats) throws ParseException {
        this.courseInstanceID = courseInstanceID;
        this.courseDetails = courseDetails;
        this.courseStartDate = DateFromString(courseStartDate);
        this.courseEndDate = DateFromString(courseEndDate);
        this.courseTerm = courseTerm;
        this.enrolledSeats = enrolledSeats;
        this.totalSeats = totalSeats;
    }

    CourseByTerm(String term) {
        this.courseTerm = term;
    }


    @Override
    public String getCourseInstanceID() {
        return courseInstanceID;
    }

    @Override
    public ICourse getCourseDetails() {
        return courseDetails;
    }

    @Override
    public Date getCourseStartDate() {
        return courseStartDate;
    }

    @Override
    public Date getCourseEndDate() {
        return courseEndDate;
    }

    @Override
    public String getCourseTerm() {
        return courseTerm;
    }

    @Override
    public Integer getEnrolledSeats() {
        return enrolledSeats;
    }

    @Override
    public Integer getTotalSeats() {
        return totalSeats;
    }

    @Override
    public void save(CourseByTermPersistence courseInstancePersistence) throws SQLException {
        courseDetails.Save();
        courseInstancePersistence.save(this);
    }

    @Override
    public ICourseByTerm loadByID(CourseByTermPersistence courseInstancePersistence, String courseInstanceId) throws SQLException, ParseException {
        ICourseByTerm courseByTerm = courseInstancePersistence.loadByID(courseInstanceId);
        return courseByTerm;
    }

    @Override
    public ArrayList<ICourseByTerm> loadByTerm(CourseByTermPersistence courseInstancePersistence, String courseTerm) throws SQLException, ParseException {
        return courseInstancePersistence.loadByTerm(courseTerm);
    }

    private Date DateFromString(String dateString) throws ParseException {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (ParseException exception) {
            return new SimpleDateFormat("dd/MM/yy").parse(dateString);
        }
    }

}
