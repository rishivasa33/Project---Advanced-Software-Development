package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.CourseInstance;
import dal.csci5308.project.group15.elearning.persistence.CourseInstancePersistence;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySqlCourseInstancePersistence implements CourseInstancePersistence {

    Database database_;

    private String GetMySqlDateString(Date date){
        String mysql_date_format_pattern = "yyyy/MM/dd";
        DateFormat df = new SimpleDateFormat(mysql_date_format_pattern);
        return df.format(date);
    }

    public MySqlCourseInstancePersistence(Database database) {
        database_ = database;
        try (Connection connection = database_.getConnection()) {
            String sql_query = "create table if not exists `course_instance` (`course_instance_id` integer, `course_id` integer," +
                    " `start_date` date, `end_date` date,  PRIMARY KEY(`course_instance_id`)," +
                    "FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE));";

            Statement statement = connection.createStatement();
            statement.execute(sql_query);
            connection.commit();
        }
        catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public void Save(CourseInstance courseInstance){
        try (Connection connection = database_.getConnection()) {
            String sql_query = "insert into course_instance (course_instance_id, course_id, start_date, end_date) values(?, ? , ?, ?) "
                    +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id=?, start_date=?, end_date=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, courseInstance.GetCourseInstanceId());
            preparedStatement.setInt(2, courseInstance.GetCourse().GetCourseID());
            preparedStatement.setString(3,GetMySqlDateString(courseInstance.GetStartDate()));
            preparedStatement.setString(4, GetMySqlDateString(courseInstance.GetEndDate()));
            preparedStatement.setInt(5, courseInstance.GetCourse().GetCourseID());
            preparedStatement.setString(6,GetMySqlDateString(courseInstance.GetStartDate()));
            preparedStatement.setString(7, GetMySqlDateString(courseInstance.GetEndDate()));
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    public CourseInstance Load(int courseInstanceId) throws ParseException{
        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT * FROM course_instance WHERE course_instance_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, courseInstanceId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int courseId = resultSet.getInt("course_id");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                connection.close();

                CourseFactory courseFactory = new CourseFactory();
                return null;
               // return courseFactory.CreateCourseInstance()
            }
            return null;

        }
        catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }
}
