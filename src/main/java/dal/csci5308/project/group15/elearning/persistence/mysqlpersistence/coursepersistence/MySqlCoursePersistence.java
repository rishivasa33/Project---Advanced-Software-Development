package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CoursePersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlCoursePersistence implements CoursePersistence {

    private Database database_;

    public MySqlCoursePersistence(Database database) {
        database_ = database;
        Connection connection = null;
        try {
            connection = database_.getConnection();
            String sql_query = "create table if not exists `course` (`course_id` varchar(12), `course_name` varchar(200)," +
                    " `course_description` varchar(1000), course_credits int(3), PRIMARY KEY(`course_id`));";

            Statement statement = connection.createStatement();
            statement.execute(sql_query);
            connection.commit();
            ;
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                    ;
                }
            } catch (SQLException exception) {
                throw new RuntimeException(exception);
            }
        }
    }


    public void Save(BaseCourse baseCourse) throws SQLException {
        try (Connection connection = database_.getConnection()) {
            String sql_query = "insert into course (course_id, course_name, course_description) values(?, ? , ?) "
                    +
                    "ON DUPLICATE KEY UPDATE " +
                    "course_name=?, course_description=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setString(1, baseCourse.GetCourseID());
            preparedStatement.setString(2, baseCourse.GetName());
            preparedStatement.setString(4, baseCourse.GetName());
            preparedStatement.setString(3, baseCourse.GetDescription());
            preparedStatement.setString(5, baseCourse.GetDescription());
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public BaseCourse Load(String course_id) {

        Connection connection = database_.getConnection();
        String sql_query = "SELECT * FROM course WHERE course_id = ?;";
        try {

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            String course_name = "";
            String course_description = "";

            while (resultSet.next()) {
                course_description = resultSet.getString("course_description");
                course_name = resultSet.getString("course_name");
            }

            ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
            return courseFactory.CreateBaseCourse(course_id, course_name, course_description);

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        } finally {
            try {
                connection.close();
            } catch (SQLException sqlException) {
                throw new RuntimeException(sqlException);
            }
        }
    }

    public int GenerateUniqueCourseID() {
        Connection connection = database_.getConnection();
        String sql_query = "SELECT MAX(course_id) as max_course_id FROM course;";
        return 1;
    }
}
