package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.BaseCourse;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.ICourseFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.GradedCoursePersistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySqlGradedCoursePersistence implements GradedCoursePersistence {
    private MySqlCoursePersistence mySqlCoursePersistence_;
    private Database database_;

    public MySqlGradedCoursePersistence(MySqlCoursePersistence mySqlCoursePersistence, Database database) {
        mySqlCoursePersistence_ = mySqlCoursePersistence;
        database_ = database;
    }

    public void Save(Course gradedCourse) throws SQLException {
        mySqlCoursePersistence_.Save(gradedCourse.GetCourseBase());

        try (Connection connection = database_.getConnection()) {
            String sql_query = "update course set course_credits=? "
                    +
                    "WHERE course_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, gradedCourse.GetCredits());
            preparedStatement.setString(2, gradedCourse.GetCourseBase().GetCourseID());
            int rows_modified = preparedStatement.executeUpdate();
            System.out.println("rows modified: " + rows_modified);
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }


    public Course Load(String course_id) throws SQLException {

        BaseCourse baseCourse = mySqlCoursePersistence_.Load(course_id);

        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT * FROM course WHERE course_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            int course_credits = 0;

            while (resultSet.next()) {
                course_credits = resultSet.getInt("course_credits");
            }

            ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();
            return courseFactory.CreateCourse(course_id, baseCourse.GetName(), baseCourse.GetDescription(), course_credits);

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }


    public ArrayList<Course> GetAllGradedCourses() throws SQLException {
        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT gc.course_id, gc.course_name, gc.course_description, gc.course_credits FROM course as gc";
            ArrayList<Course> courseArrayList = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement.executeQuery();
            ICourseFactory courseFactory = FactoryFacade.instance().getCourseFactory();

            while (resultSet.next()) {
                String course_id = resultSet.getString("gc.course_id");
                int total_credits = resultSet.getInt("gc.course_credits");
                String course_name = resultSet.getString("gc.course_name");
                String course_description = resultSet.getString("gc.course_description");
                courseArrayList.add(courseFactory.CreateCourse(course_id, course_name, course_description, total_credits));
            }

            return courseArrayList;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
