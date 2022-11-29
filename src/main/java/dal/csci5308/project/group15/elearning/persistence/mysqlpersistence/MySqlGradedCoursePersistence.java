package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;

import java.sql.*;
import java.util.ArrayList;

public class MySqlGradedCoursePersistence implements GradedCoursePersistence {
    private MySqlCoursePersistence mySqlCoursePersistence_;
    private Database database_;
    public MySqlGradedCoursePersistence(MySqlCoursePersistence mySqlCoursePersistence, Database database){
        mySqlCoursePersistence_ = mySqlCoursePersistence;
        database_ = database;
    }
    public void Save(GradedCourse graded_course) throws SQLException {

        mySqlCoursePersistence_.Save(graded_course.GetCourse());

        try (Connection connection = database_.getConnection()) {
            String sql_query = "update course set course_credits=? "
                    +
                    "WHERE course_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, graded_course.GetCredits());
            preparedStatement.setString(2, graded_course.GetCourse().GetCourseID());
            int rows_modified = preparedStatement.executeUpdate();
            System.out.println("rows modified: " + rows_modified);
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }

    public GradedCourse Load(String course_id) throws SQLException {

        Course course = mySqlCoursePersistence_.Load(course_id);

        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT * FROM course WHERE course_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            int course_credits = 0;

            while (resultSet.next()) {
                course_credits = resultSet.getInt("course_credits");
            }

            CourseFactory courseFactory = new CourseFactory();
            return courseFactory.CreateGradedCourse(course_id, course.GetName(), course.GetDescription(), course_credits);

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public ArrayList<GradedCourse> GetAllGradedCourses() throws SQLException {
        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT gc.course_id, gc.course_name, gc.course_description, gc.course_credits FROM course as gc";
            ArrayList<GradedCourse> gradedCourseArrayList = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement.executeQuery();
            CourseFactory courseFactory = new CourseFactory();

            while (resultSet.next()) {
                String course_id = resultSet.getString("gc.course_id");
                int total_credits = resultSet.getInt("gc.course_credits");
                String course_name = resultSet.getString("gc.course_name");
                String course_description = resultSet.getString("gc.course_description");
                gradedCourseArrayList.add(courseFactory.CreateGradedCourse(course_id, course_name, course_description, total_credits));
            }


            return gradedCourseArrayList;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
