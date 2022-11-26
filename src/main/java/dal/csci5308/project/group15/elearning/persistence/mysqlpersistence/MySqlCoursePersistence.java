package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;

import java.sql.*;
import java.util.Random;

public class MySqlCoursePersistence implements CoursePersistence {

    private Database database_;

    public MySqlCoursePersistence(Database database){
        database_ = database;
        Connection connection = null;
        try {
            connection = database_.getConnection();
            String sql_query = "create table if not exists `course` (`course_id` integer, `course_name` varchar(200)," +
                    " `course_description` varchar(1000), PRIMARY KEY(`course_id`));";

            Statement statement =  connection.createStatement();
            statement.execute(sql_query);
            connection.commit();;
        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            try {
                if (connection != null) {
                    connection.close();
                    ;
                }
            }
            catch (SQLException exception){
                throw new RuntimeException(exception);
            }
        }
    }



    public void Save(Course course) throws SQLException {
        try (Connection connection = database_.getConnection()) {
            String sql_query = "insert into course (course_id, course_name, course_description) values(?, ? , ?) "
                    +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_name=?, course_description=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, course.GetCourseID());
            preparedStatement.setString(2, course.GetName());
            preparedStatement.setString(4, course.GetName());
            preparedStatement.setString(3, course.GetDescription());
            preparedStatement.setString(5, course.GetDescription());
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }


    }

    public Course Load(int course_id) {

        Connection connection = database_.getConnection();
        String sql_query = "SELECT * FROM course WHERE course_id = ?;";
        try {

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            String course_name = "";
            String course_description = "";

            while(resultSet.next()){
                course_description =  resultSet.getString("course_description");
                course_name =resultSet.getString("course_name");
            }

            CourseFactory courseFactory = new CourseFactory();
            return courseFactory.CreateCourse(course_id, course_name, course_description);

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException sqlException){
                throw  new RuntimeException(sqlException);
            }
        }
    }

    public int GenerateUniqueCourseID(){
        Connection connection = database_.getConnection();
        String sql_query = "SELECT MAX(course_id) as max_course_id FROM course;";
        try {
            connection.setAutoCommit(false);

            PreparedStatement statement = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement.executeQuery();
            int max_course_id = -1;

            while(resultSet.next()){
               max_course_id =  resultSet.getInt("max_course_id");
            }
            connection.commit();

           return max_course_id + 1;

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            try {
                connection.close();
            }
            catch (SQLException sqlException){
                throw  new RuntimeException(sqlException);
            }
        }
    }
}
