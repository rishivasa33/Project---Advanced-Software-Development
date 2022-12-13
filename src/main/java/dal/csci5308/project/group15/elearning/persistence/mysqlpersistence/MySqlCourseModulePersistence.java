package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.CourseFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.persistence.CourseModulePersistence;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;

public class MySqlCourseModulePersistence implements CourseModulePersistence {
    Database database;
    public MySqlCourseModulePersistence(){
        database = Database.instance();
    }
    public int Save(CourseModule courseModule, String courseID) throws SQLException {


            try (Connection connection = database.getConnection()) {
                if(courseModule.GetCourseModuleId() == null) {
                    String sql_query = "insert into course_module(course_module_name,  course_id) values(? , ?);";


                    PreparedStatement preparedStatement = connection.prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, courseModule.GetModuleName());
                    preparedStatement.setString(2, courseID);
                    int rows_modified = preparedStatement.executeUpdate();
                    connection.commit();
                    ResultSet rs = preparedStatement.getGeneratedKeys();
                    rs.next();
                    System.out.println("check save 2");
                    int id =  rs.getInt(1);
                    System.out.println("check id " + id);
                    return id;
                }
                else {
                    String sql_query = "update course_module set course_module_name = ?,  course_id = ?)  where course_module_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
                    preparedStatement.setString(1, courseModule.GetModuleName());
                    preparedStatement.setString(2, courseID);
                    preparedStatement.setInt(3, courseModule.GetCourseModuleId());
                    int rows_modified = preparedStatement.executeUpdate();
                    connection.commit();
                    return courseModule.GetCourseModuleId();
                }

            } catch (SQLException sqlException) {
                throw sqlException;
            }
    }




    public CourseModule Load(int courseModuleID) throws SQLException {
        CourseContentFactory courseContentFactory = new CourseContentFactory();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module WHERE course_module_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, courseModuleID);
            ResultSet resultSet = statement.executeQuery();

            String course_module_name = "";

            while (resultSet.next()) {
                course_module_name = resultSet.getString("course_module_name");
            }

            return courseContentFactory.CreateCourseModule(course_module_name);

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public ArrayList<CourseModule> GetAllModulesInCourse(String courseId) throws SQLException {
        CourseContentFactory courseContentFactory = new CourseContentFactory();
        ArrayList<CourseModule> courseModuleArrayList = new ArrayList<>();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module WHERE course_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setString(1, courseId);
            ResultSet resultSet = statement.executeQuery();

            String course_module_name = "";
            int courseModuleId = 0;

            while (resultSet.next()) {
                course_module_name = resultSet.getString("course_module_name");
                courseModuleId = resultSet.getInt("course_module_id");
                CourseModule courseModule =  courseContentFactory.CreateCourseModule(course_module_name,courseModuleId);
                courseModuleArrayList.add(courseModule);
            }

            return courseModuleArrayList;

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}
