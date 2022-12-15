package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlStudentCourseEnrollment implements IStudentCourseEnrollmentPersistence {

    IDatabaseOperations databaseOperations = DatabaseOperations.instance();
    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @Override
    public Integer save(StudentCourseEnrollment studentCourseEnrollment) throws SQLException {
        try {
            return databaseOperations.create(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_SAVE"),
                    studentCourseEnrollment.getStudentNumber(), studentCourseEnrollment.getCourseInstanceID(), studentCourseEnrollment.getCourseTerm());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByStudentNumber(String studentNumber) throws SQLException, ParseException {

        ArrayList<IStudentCourseEnrollment> studentEnrolledCourses;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_LOAD_BY_STUDENT_NO"), studentNumber);

            studentEnrolledCourses = parseStudentCourseEnrollmentsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentEnrolledCourses;
    }


    @Override
    public ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(String courseInstanceId) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentEnrolledCourses;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_LOAD_BY_COURSE_INSTANCE_ID"), courseInstanceId);

            studentEnrolledCourses = parseStudentCourseEnrollmentsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentEnrolledCourses;
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(String studentNumber, String courseTerm) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourses = new ArrayList<>();
        ArrayList<IStudentCourseEnrollment> studentEnrolledCourses;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_LOAD_BY_TERM_AND_STUDENT_NO"), courseTerm, studentNumber);

            studentEnrolledCourses = parseStudentCourseEnrollmentsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return studentEnrolledCourses;
    }

    @Override
    public Integer loadStudentCourseCountByTerm(String studentNumber, String courseTerm) {
        Integer courseCount = 0;
        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_VALIDATE_BY_COURSE_COUNT"), studentNumber, courseTerm);

            courseCount = parseValidationResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courseCount;
    }

    @Override
    public Integer loadStudentCreditCountByTerm(String studentNumber, String courseTerm) {
        Integer creditsCount = 0;
        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_COURSE_ENROLLMENT_VALIDATE_BY_CREDIT_COUNT"), studentNumber, courseTerm);

            creditsCount = parseValidationResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return creditsCount;
    }

    private ArrayList<IStudentCourseEnrollment> parseStudentCourseEnrollmentsToList(Map<String, List<Object>> resultSet) {
        IStudentFactory studentFactory = FactoryFacade.instance().getStudentFactory();
        ArrayList<IStudentCourseEnrollment> studentCourseEnrollments = new ArrayList<>();
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            String studentNumber = String.valueOf(databaseOperations.getValueAt(resultSet, "student_number", row));
            String courseInstanceID = String.valueOf(databaseOperations.getValueAt(resultSet, "course_instance_id", row));
            String courseTerm = String.valueOf(databaseOperations.getValueAt(resultSet, "course_term", row));

            IStudentCourseEnrollment enrollment = studentFactory.createStudentCourseEnrollmentInstance(courseInstanceID, studentNumber, courseTerm);
            studentCourseEnrollments.add(enrollment);
        }
        return studentCourseEnrollments;
    }

    private Integer parseValidationResultSet(Map<String, List<Object>> resultSet) {
        Integer courseCount = 0;
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            Long count = (Long) (databaseOperations.getValueAt(resultSet, "COUNT", row));
            courseCount = count.intValue();
        }
        return courseCount;
    }

}
