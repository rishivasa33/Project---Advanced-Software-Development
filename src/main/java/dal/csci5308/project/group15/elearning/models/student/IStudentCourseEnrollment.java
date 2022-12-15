package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.IValidationState;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface IStudentCourseEnrollment {

    String getCourseInstanceID();

    String getStudentNumber();

    String getCourseTerm();

    Integer getEnrolledSeats();

    Integer getTotalSeats();

    String saveBasedOnCourseCount(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence) throws SQLException;

    String saveBasedOnCreditCount(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence) throws SQLException;

    IValidationState validateSaveByCourseCount();

    IValidationState validateSaveByCreditsCount();

    ArrayList<IStudentCourseEnrollment> loadByStudentNumber(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber) throws SQLException, ParseException;

    ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String courseInstanceId) throws SQLException, ParseException;

    ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm) throws SQLException, ParseException;

    Integer loadStudentCourseCountByTerm(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm);

    Integer loadStudentCreditCountByTerm(IStudentCourseEnrollmentPersistence iStudentCourseEnrollmentPersistence, String studentNumber, String courseTerm);

}
