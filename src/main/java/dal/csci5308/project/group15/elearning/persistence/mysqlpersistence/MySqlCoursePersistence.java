package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistence;

import java.sql.*;

public class MySqlCoursePersistence implements CoursePersistence {

    private Database database_;

    public MySqlCoursePersistence(Database database) throws SQLException {
        database_ = database;
        try {
            String sql_query = "create table if not exists `course` (`course_id` integer, `course_name` varchar(200)," +
                    " `course_description` varchar(1000), PRIMARY KEY(`course_id`));";

            Statement statement =  database_.getConnection().createStatement();
            statement.execute(sql_query);
            database_.getConnection().commit();
            database_.getConnection().close();
        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            database_.getConnection().close();
        }
    }



    public void Save(Course course) throws SQLException {
        Connection connection = database_.getConnection();
        String sql_query = "insert into course (course_id, course_name, course_description) values(?, ? , ?) "
                +
                "ON DUPLICATE KEY UPDATE " +
                "course_name=?, course_description=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, course.GetCourseID());
            preparedStatement.setString(2, course.GetName());
            preparedStatement.setString(4, course.GetName());
            preparedStatement.setString(3, course.GetDescription());
            preparedStatement.setString(5, course.GetDescription());
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            database_.getConnection().close();
        }

    }

    public Course Load(int course_id){

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
            database_.getConnection();
        }
    }
}
