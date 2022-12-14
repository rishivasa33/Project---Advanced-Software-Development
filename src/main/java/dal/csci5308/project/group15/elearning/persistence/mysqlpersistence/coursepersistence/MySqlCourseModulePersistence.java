package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.*;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseModulePersistence;

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
                    int id =  rs.getInt(1);
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
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

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

    public ArrayList<CourseContent> GetAllContentsInAModule(int courseModuleId) throws SQLException{
        ArrayList<CourseContent> courseContents = new ArrayList<>();
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

        //CALL `CSCI5308_15_DEVINT`.`GetAllContentsInModule`(<{IN courseModuleId INT}>);

        try (Connection connection = database.getConnection()) {
            CallableStatement cStmt = connection.prepareCall("{call GetAllContentsInModule (?)}");
            cStmt.setInt("courseModuleId", courseModuleId);

            boolean hasResult = cStmt.execute();
            ResultSet resultSet = cStmt.getResultSet();
            while(resultSet.next()){
                String courseModuleHeading = resultSet.getString("courseModuleContentName");
                String courseModuleText = resultSet.getString("courseModuleContentText");
                int courseModuleContentId = resultSet.getInt("courseModuleContentId");
                String courseModuleContentType = resultSet.getString("courseModuleContentType");
                String courseModuleContentFilePath = resultSet.getString("courseModuleContentFilePath");
                if(courseModuleContentType.equals("TEXT")) {
                    CourseContent courseContent = courseContentFactory.CreateTextCourseContent(courseModuleContentId, courseModuleHeading, courseModuleText);
                    courseContents.add(courseContent);
                }
                else {
                    CourseContent courseContent = courseContentFactory.CreateFileCourseContent(courseModuleContentId, courseModuleHeading, courseModuleContentFilePath);
                    courseContents.add(courseContent);
                }
            }
            connection.commit();
            return courseContents;

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }


    public ArrayList<CourseModule> GetAllModulesInCourse(String courseId) throws SQLException {
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();
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

    public CourseContent LoadCourseContent(int courseContentId) throws SQLException {
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module_content WHERE courseModuleContentId = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, courseContentId);
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {
                String courseModuleHeading = resultSet.getString("courseModuleContentName");
                String courseModuleText = resultSet.getString("courseModuleContentText");
                int courseModuleContentId = resultSet.getInt("courseModuleContentId");
                String courseModuleContentType = resultSet.getString("courseModuleContentType");
                String courseModuleContentFilePath = resultSet.getString("courseModuleContentFilePath");
                if(courseModuleContentType.equals("TEXT")) {
                    CourseContent courseContent = courseContentFactory.CreateTextCourseContent(courseModuleContentId, courseModuleHeading, courseModuleText);
                     return courseContent;
                }
                else {
                    CourseContent courseContent = courseContentFactory.CreateFileCourseContent(courseModuleContentId, courseModuleHeading, courseModuleContentFilePath);
                    return courseContent;
                }
            }
            throw  new SQLException("Invalid course Content Id");

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}
