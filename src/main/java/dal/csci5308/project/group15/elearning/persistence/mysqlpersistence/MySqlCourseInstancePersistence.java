package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.CourseInstance;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
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
            String sql_query = "create table if not exists `course_by_term` (`course_instance_id` integer, `course_id` integer," +
                    " `start_date` date, `end_date` date, course_term VARCHAR(50),   PRIMARY KEY(`course_instance_id`)," +
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
            String sql_query = "insert into course_by_term (course_instance_id, course_id, start_date, end_date) values(?, ? , ?, ?) "
                    +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_id=?, start_date=?, end_date=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setString(1, courseInstance.GetCourseInstanceId());
            preparedStatement.setString(2, courseInstance.GetCourse().GetCourseID());
            preparedStatement.setString(3,GetMySqlDateString(courseInstance.GetStartDate()));
            preparedStatement.setString(4, GetMySqlDateString(courseInstance.GetEndDate()));
            preparedStatement.setString(5, courseInstance.GetCourse().GetCourseID());
            preparedStatement.setString(6,GetMySqlDateString(courseInstance.GetStartDate()));
            preparedStatement.setString(7, GetMySqlDateString(courseInstance.GetEndDate()));
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }

    public CourseInstance Load(String courseInstanceId) throws ParseException{
        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT * FROM course_instance WHERE course_instance_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, courseInstanceId);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                String courseId = resultSet.getString("course_id");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                connection.close();

                CourseFactory courseFactory = new CourseFactory();
                GradedCourse gradedCourse =  courseFactory.CreateGradedCourse("test", "test", "test", 20);
                gradedCourse =  gradedCourse.Load(courseId);

                return courseFactory.CreateCourseInstance(gradedCourse, start_date, end_date);
            }
            return null;

        }
        catch (SQLException sqlException) {
            throw new RuntimeException();
        }
    }
}
