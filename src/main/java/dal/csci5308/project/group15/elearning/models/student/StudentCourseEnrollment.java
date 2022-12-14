package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.EnrollmentValidatorByCourseCount;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.EnrollmentValidatorByCreditsCount;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.EnrollmentValidatorTemplate;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.IValidationState;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class StudentCourseEnrollment implements IStudentCourseEnrollment {

    private String courseInstanceID;
    private String studentNumber;
    private String courseTerm;
    private Integer enrolledSeats;
    private Integer totalSeats;

    private IValidationState validationState;

    public StudentCourseEnrollment(String courseInstanceID, String studentNumber, String courseTerm) {
        this.courseInstanceID = courseInstanceID;
        this.studentNumber = studentNumber;
        this.courseTerm = courseTerm;
    }

    public StudentCourseEnrollment(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public StudentCourseEnrollment(String courseInstanceID, String studentNumber, String courseTerm, Integer enrolledSeats, Integer totalSeats) {
        this.courseInstanceID = courseInstanceID;
        this.studentNumber = studentNumber;
        this.courseTerm = courseTerm;
        this.enrolledSeats = enrolledSeats;
        this.totalSeats = totalSeats;
    }

    @Override
    public String getCourseInstanceID() {
        return courseInstanceID;
    }

    @Override
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String getCourseTerm() {
        return courseTerm;
    }

    public Integer getEnrolledSeats() {
        return enrolledSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    @Override
    public String saveBasedOnCourseCount(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence) throws SQLException {
        if (validateSaveByCourseCount().isValid()) {
            iStudentCourseEnrollmentPersistence.save(this);
        }
        return validationState.getValidationResult();
    }

    @Override
    public String saveBasedOnCreditCount(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence) throws SQLException {
        if (validateSaveByCreditsCount().isValid()) {
            iStudentCourseEnrollmentPersistence.save(this);
        }
        return validationState.getValidationResult();
    }


    @Override
    public IValidationState validateSaveByCourseCount() {

        EnrollmentValidatorTemplate registrationValidator = new EnrollmentValidatorByCourseCount();

        validationState = registrationValidator.validateStudentRegistration(this);

        return validationState;
    }

    @Override
    public IValidationState validateSaveByCreditsCount() {
        EnrollmentValidatorTemplate registrationValidator = new EnrollmentValidatorByCreditsCount();

        validationState = registrationValidator.validateStudentRegistration(this);

        return validationState;
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
    public Integer loadStudentCourseCountByTerm(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm) {
        return iStudentCourseEnrollmentPersistence.loadStudentCourseCountByTerm(studentNumber, courseTerm);
    }

    @Override
    public Integer loadStudentCreditCountByTerm(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm) {
        return iStudentCourseEnrollmentPersistence.loadStudentCreditCountByTerm(studentNumber, courseTerm);
    }

    @Override
    public String toString() {
        return "StudentCourseEnrollment{" +
                "courseInstanceID='" + courseInstanceID + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", courseTerm='" + courseTerm + '\'' +
                '}';
    }
}
