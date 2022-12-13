package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.models.course.ICourseByTerm;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class StudentCourseEnrollment implements IStudentCourseEnrollment {

    private String courseInstanceID;
    private String studentNumber;

    private String courseTerm;
    private ICourseByTerm courseInstance;

    public StudentCourseEnrollment(String courseInstanceID, String studentNumber, ICourseByTerm courseInstance) {
        this.courseInstanceID = courseInstanceID;
        this.studentNumber = studentNumber;
        this.courseInstance = courseInstance;
    }

    public StudentCourseEnrollment(String courseInstanceID, String studentNumber, String courseTerm) {
        this.courseInstanceID = courseInstanceID;
        this.studentNumber = studentNumber;
        this.courseTerm = courseTerm;
    }

    public StudentCourseEnrollment(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getCourseInstanceID() {
        return courseInstanceID;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getCourseTerm() {
        return courseTerm;
    }

    public ICourseByTerm getCourseInstance() {
        return courseInstance;
    }

    @Override
    public void save(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence) throws SQLException {
        iStudentCourseEnrollmentPersistence.save(this);
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByStudentNumber(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber) throws SQLException, ParseException {
        return iStudentCourseEnrollmentPersistence.loadByStudentNumber(studentNumber);
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String courseInstanceId) throws SQLException, ParseException {
        return iStudentCourseEnrollmentPersistence.loadByCourseInstanceID(courseInstanceId);
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm) throws SQLException, ParseException {
        return iStudentCourseEnrollmentPersistence.loadByTermAndStudentNumber(studentNumber, courseTerm);
    }

    @Override
    public String toString() {
        return "StudentCourseEnrollment{" +
                "courseInstanceID='" + courseInstanceID + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", courseTerm='" + courseTerm + '\'' +
                ", courseInstance=" + courseInstance +
                '}';
    }
}
