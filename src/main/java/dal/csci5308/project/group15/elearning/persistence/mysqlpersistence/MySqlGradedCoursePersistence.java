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
        try {
            String sql_query = "create table if not exists `graded_course` (`course_id` integer, `total_credits` integer," +
                    "PRIMARY KEY(`course_id`), FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE);";

            Statement statement =  database_ .GetConnection().createStatement();
            statement.execute(sql_query);
            database_ .GetConnection().commit();
            database_ .GetConnection().close();
        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            database_ .CloseConnection();
        }



    }
    public void Save(GradedCourse graded_course){

        mySqlCoursePersistence_.Save(graded_course.GetCourse());

        Connection connection = database_.GetConnection();
        String sql_query = "insert into graded_course (course_id, total_credits) values(?, ?) "
                +
                "ON DUPLICATE KEY UPDATE " +
                "total_credits=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
            preparedStatement.setInt(1, graded_course.GetCourse().GetCourseID());
            preparedStatement.setInt(2, graded_course.GetCredits());
            preparedStatement.setInt(3, graded_course.GetCredits());
            int rows_modified = preparedStatement.executeUpdate();
            connection.commit();

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
           database_.CloseConnection();
        }

    }

    public GradedCourse Load(int course_id){

        Course course = mySqlCoursePersistence_.Load(course_id);

        Connection connection = database_.GetConnection();
        String sql_query = "SELECT * FROM graded_course WHERE course_id = ?;";
        try {

           PreparedStatement statement = connection.prepareStatement(sql_query);
           statement.setInt(1, course_id);
            ResultSet resultSet = statement.executeQuery();
            int total_credits = 0;

            while(resultSet.next()){
                total_credits =  resultSet.getInt("total_credits");
            }

            CourseFactory courseFactory = new CourseFactory();
            return courseFactory.CreateGradedCourse(course_id, course.GetName(), course.GetDescription(), total_credits);

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            database_.CloseConnection();
        }
    }

    public ArrayList<GradedCourse> GetAllGradedCourses(){
        Connection connection = database_.GetConnection();
        String sql_query = "SELECT gc.course_id, c.course_name, c.course_description, gc.total_credits FROM graded_course as gc INNER JOIN course as c ON c.course_id = gc.course_id;";
        ArrayList<GradedCourse> gradedCourseArrayList = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(sql_query);
            ResultSet resultSet = statement.executeQuery();
            CourseFactory courseFactory = new CourseFactory();

            while(resultSet.next()){
                int course_id = resultSet.getInt("gc.course_id");
                int total_credits =  resultSet.getInt("gc.total_credits");
                String course_name = resultSet.getString("c.course_name");
                String course_description = resultSet.getString("c.course_description");
                gradedCourseArrayList.add(courseFactory.CreateGradedCourse(course_id, course_name, course_description, total_credits));
            }


            return gradedCourseArrayList;

        }
        catch (SQLException sqlException){
            throw  new RuntimeException(sqlException);
        }
        finally {
            database_.CloseConnection();
        }
    }
}
