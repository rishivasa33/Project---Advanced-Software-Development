package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlStudentCourseEnrollment implements IStudentCourseEnrollmentPersistence {

    IDatabaseOperations databaseOperations = DatabaseOperations.instance();
    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
    Database database;

    public MySqlStudentCourseEnrollment(Database database) {
        this.database = database;
    }

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
        ArrayList<IStudentCourseEnrollment> studentCourses = new ArrayList<>();

        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM student_course_enrollment WHERE student_number = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentNumber);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseInstanceId = resultSet.getString("course_instance_id");
                String courseTerm = resultSet.getString("course_term");

                IStudentFactory courseFactory = FactoryFacade.instance().getStudentFactory();

                IStudentCourseEnrollment studentCourseEnrollment = courseFactory.createStudentCourseEnrollmentInstance(courseInstanceId, studentNumber, courseTerm);
                studentCourses.add(studentCourseEnrollment);
            }
            return studentCourses;

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByCourseInstanceID(String courseInstanceId) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourses = new ArrayList<>();

        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM student_course_enrollment WHERE course_instance_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, courseInstanceId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String studentNumber = resultSet.getString("student_number");
                String courseTerm = resultSet.getString("course_term");

                IStudentFactory courseFactory = FactoryFacade.instance().getStudentFactory();

                IStudentCourseEnrollment studentCourseEnrollment = courseFactory.createStudentCourseEnrollmentInstance(courseInstanceId, studentNumber, courseTerm);
                studentCourses.add(studentCourseEnrollment);
            }
            return studentCourses;

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public ArrayList<IStudentCourseEnrollment> loadByTermAndStudentNumber(String studentNumber, String courseTerm) throws SQLException, ParseException {
        ArrayList<IStudentCourseEnrollment> studentCourses = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql = "SELECT * FROM student_course_enrollment WHERE student_number = ? and course_term = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, studentNumber);
            statement.setString(2, courseTerm);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseInstanceId = resultSet.getString("course_instance_id");
                IStudentFactory courseFactory = FactoryFacade.instance().getStudentFactory();

                IStudentCourseEnrollment studentCourseEnrollment = courseFactory.createStudentCourseEnrollmentInstance(courseInstanceId, studentNumber, courseTerm);
                studentCourses.add(studentCourseEnrollment);
            }
            return studentCourses;

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
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

    private Integer parseValidationResultSet(Map<String, List<Object>> resultSet) {
        Integer courseCount = 0;
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            Long count = (Long) (databaseOperations.getValueAt(resultSet, "COUNT", row));
            courseCount = count.intValue();
        }
        return courseCount;
    }


}
