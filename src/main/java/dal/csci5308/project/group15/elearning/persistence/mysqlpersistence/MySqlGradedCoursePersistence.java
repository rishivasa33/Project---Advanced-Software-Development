package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.Course;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.persistence.CoursePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.GradedCoursePersistence;

import java.sql.*;
import java.util.ArrayList;

public class MySqlGradedCoursePersistence extends GradedCoursePersistence {
    private MySqlCoursePersistence mySqlCoursePersistence_;
    private Database database_;
    public MySqlGradedCoursePersistence(MySqlCoursePersistence mySqlCoursePersistence, Database database){
        mySqlCoursePersistence_ = mySqlCoursePersistence;
        database_ = database;
        try (Connection connection = database_.getConnection()) {
            String sql_query = "create table if not exists `graded_course` (`course_id` integer, `total_credits` integer," +
                    "PRIMARY KEY(`course_id`), FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE);";

            Statement statement = connection.createStatement();
            statement.execute(sql_query);
            connection.commit();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }


    }
    public void Save(GradedCourse gradedCourse) throws SQLException {


        mySqlCoursePersistence_.Save(gradedCourse.GetCourse());

        try (Connection connection = database_.getConnection()) {
            String sql_query = "insert into graded_course (course_id, total_credits) values(?, ?) "
                    +
                    "ON DUPLICATE KEY UPDATE " +
                    "total_credits=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, gradedCourse.GetCourse().GetCourseID());
            preparedStatement.setInt(2, gradedCourse.GetCredits());
            preparedStatement.setInt(3, gradedCourse.GetCredits());
            int rows_modified = preparedStatement.executeUpdate();
            System.out.println("rows modified: " + rows_modified);
            connection.commit();

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }

    }


    public GradedCourse Load(int course_id) throws SQLException {

        Course course = mySqlCoursePersistence_.Load(course_id);

        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT * FROM graded_course WHERE course_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            int total_credits = 0;

            while (resultSet.next()) {
                total_credits = resultSet.getInt("total_credits");
            }

            CourseFactory courseFactory = new CourseFactory();
            return courseFactory.CreateGradedCourse(course_id, course.GetName(), course.GetDescription(), total_credits);

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    public ArrayList<GradedCourse> GetAllGradedCourses(){
        try (Connection connection = database_.getConnection()) {
            String sql_query = "SELECT gc.course_id, c.course_name, c.course_description, gc.total_credits FROM graded_course as gc INNER JOIN course as c ON c.course_id = gc.course_id;";
            ArrayList<GradedCourse> gradedCourseArrayList = new ArrayList<>();

            PreparedStatement statement = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement.executeQuery();
            CourseFactory courseFactory = new CourseFactory();

            while (resultSet.next()) {
                int course_id = resultSet.getInt("gc.course_id");
                int total_credits = resultSet.getInt("gc.total_credits");
                String course_name = resultSet.getString("c.course_name");
                String course_description = resultSet.getString("c.course_description");
                gradedCourseArrayList.add(courseFactory.CreateGradedCourse(course_id, course_name, course_description, total_credits));
            }


            return gradedCourseArrayList;

        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }
}
