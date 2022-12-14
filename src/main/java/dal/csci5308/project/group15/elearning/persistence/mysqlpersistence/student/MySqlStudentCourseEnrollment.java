package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.IStudentFactory;
import dal.csci5308.project.group15.elearning.models.student.StudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentCourseEnrollmentPersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class MySqlStudentCourseEnrollment implements IStudentCourseEnrollmentPersistence {

    Database database;

    public MySqlStudentCourseEnrollment(Database database) {
        this.database = database;
    }

    @Override
    public String save(StudentCourseEnrollment studentCourseEnrollment) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String query = "insert into student_course_enrollment (student_number, course_instance_id, course_term) values(?, ? ,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentCourseEnrollment.getStudentNumber());
            preparedStatement.setString(2, studentCourseEnrollment.getCourseInstanceID());
            preparedStatement.setString(3, studentCourseEnrollment.getCourseTerm());
            int modifiedRows = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
        return "SUCCESS";
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
}
