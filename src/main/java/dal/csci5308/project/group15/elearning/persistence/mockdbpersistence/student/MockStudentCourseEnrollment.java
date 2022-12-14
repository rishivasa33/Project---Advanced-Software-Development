package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student;

import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class MockStudentCourseEnrollment implements IStudentCourseEnrollmentPersistence {
    @Override
    public Integer save(StudentCourseEnrollment studentCourseEnrollment) throws SQLException {
        System.out.println("Save MockStudentCourseEnrollment");
        return 1;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByStudentNumber(String studentNumber) throws SQLException, ParseException {
        return null;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(String courseInstanceId) throws SQLException, ParseException {
        return null;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(String studentNumber, String courseTerm) throws SQLException, ParseException {
        return null;
    }

    @Override
    public Integer loadStudentCourseCountByTerm(String studentNumber, String courseTerm) {
        return null;
    }

    @Override
    public Integer loadStudentCreditCountByTerm(String studentNumber, String courseTerm) {
        return null;
    }
}
