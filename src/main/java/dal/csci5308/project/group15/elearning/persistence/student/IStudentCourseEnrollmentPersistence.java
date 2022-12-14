package dal.csci5308.project.group15.elearning.persistence.student;

import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface IStudentCourseEnrollmentPersistence {

    Integer save(StudentCourseEnrollment studentCourseEnrollment) throws SQLException;

    ArrayList<IStudentCourseEnrollment> loadByStudentNumber(String studentNumber) throws SQLException, ParseException;

    ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(String courseInstanceId) throws SQLException, ParseException;

    ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(String studentNumber, String courseTerm) throws SQLException, ParseException;

    Integer loadStudentCourseCountByTerm(String studentNumber, String courseTerm);

    Integer loadStudentCreditCountByTerm(String studentNumber, String courseTerm);
}