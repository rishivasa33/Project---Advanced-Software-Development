package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseByTerm;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CourseInstancePersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MySqlCourseInstancePersistence implements CourseInstancePersistence {

    Database database;

    public MySqlCourseInstancePersistence(Database database) {
        this.database = database;
/*        try (Connection connection = this.database.getConnection()) {
            String sql_query = "create table if not exists `course_by_term` (`course_instance_id` integer, `course_id` integer," +
                    " `start_date` date, `end_date` date, course_term VARCHAR(50),   PRIMARY KEY(`course_instance_id`)," +
                    "FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE));";

            Statement statement = connection.createStatement();
            statement.execute(sql_query);
            connection.commit();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }*/
    }

    private String GetMySqlDateString(Date date) {
        String mysql_date_format_pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(mysql_date_format_pattern);
        return df.format(date);
    }

    @Override
    public void save(CourseByTerm courseByTerm) throws SQLException {
        try (Connection connection = database.getConnection()) {
            String sql_query = "insert into course_by_term (course_instance_id, course_id, start_date, end_date, course_term, enrolled_seats, total_seats) values(?, ? , ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setString(1, courseByTerm.getCourseInstanceID());
            preparedStatement.setString(2, courseByTerm.getCourseDetails().GetCourseID());
            preparedStatement.setString(3, GetMySqlDateString(courseByTerm.getCourseStartDate()));
            preparedStatement.setString(4, GetMySqlDateString(courseByTerm.getCourseEndDate()));
            preparedStatement.setString(5, courseByTerm.getCourseTerm());
            preparedStatement.setInt(6, courseByTerm.getEnrolledSeats());
            preparedStatement.setInt(7, courseByTerm.getTotalSeats());
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    public CourseByTerm loadByID(String courseInstanceId) throws ParseException {
        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_by_term WHERE course_instance_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, courseInstanceId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseId = resultSet.getString("course_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String courseTerm = resultSet.getString("course_term");
                Integer enrolledSeats = resultSet.getInt("enrolled_seats");
                Integer totalSeats = resultSet.getInt("total_seats");

                ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
                Course course = courseFactory.createCourseInstanceForLoad(courseId);
                course = course.Load(courseId);

                return courseFactory.CreateCourseInstance(courseInstanceId, course, startDate, endDate, courseTerm, enrolledSeats, totalSeats);
            }
            return null;

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    @Override
    public ArrayList<ICourseByTerm> loadByTerm(String courseTerm) throws ParseException {
        ArrayList<ICourseByTerm> coursesByTerm = new ArrayList<>();
        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_by_term WHERE course_term = ?;";
            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, courseTerm);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String courseInstanceId = resultSet.getString("course_instance_id");
                String courseId = resultSet.getString("course_id");
                Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                Integer enrolledSeats = resultSet.getInt("enrolled_seats");
                Integer totalSeats = resultSet.getInt("total_seats");

                ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
                Course course = courseFactory.createCourseInstanceForLoad(courseId);
                course = course.Load(courseId);

                ICourseByTerm courseInstance = courseFactory.CreateCourseInstance(courseInstanceId, course, startDate, endDate, courseTerm, enrolledSeats, totalSeats);
                coursesByTerm.add(courseInstance);
            }
            return coursesByTerm;

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }
}
