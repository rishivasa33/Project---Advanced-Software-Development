package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.coursecontentpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.TextCourseContentPersistence;

import java.sql.*;
import java.util.ArrayList;

public class MySqlTextCourseContentPersistence implements TextCourseContentPersistence {

    private Database database;

    public MySqlTextCourseContentPersistence(){
        database = Database.instance();
    }
    public int Save(TextCourseContent courseContent, int courseModuleId) throws SQLException {


        try (Connection connection = database.getConnection()) {
            if(courseContent.GetContentId() == null) {

                CallableStatement cStmt = connection.prepareCall("{call InsertTextCourseContent (?, ?, ?, ?)}");
                cStmt.registerOutParameter("courseModuleContentId", Types.INTEGER);
                cStmt.setInt("courseModuleId", courseModuleId);
                cStmt.setString("courseModuleContentName", courseContent.GetContentHeading());
                cStmt.setString("courseModuleContentText", courseContent.GetTextContent());

                boolean hasResult = cStmt.execute();
                while(hasResult){
                    ResultSet resultSet = cStmt.getResultSet();
                    int courseModuleContentId = cStmt.getInt("courseModuleContentId");
                    connection.commit();
                    return courseModuleContentId;

                }
                throw new SQLException("Could not generate courseModuleContentId");
            }
            else {
                CallableStatement cStmt = connection.prepareCall("{call UpdateTextCourseContent (?, ?,  ?, ?)}");
                cStmt.setInt("courseModuleContentId", courseContent.GetContentId());
                cStmt.setInt("courseModuleId", courseModuleId);
                cStmt.setString("courseModuleContentName", courseContent.GetContentHeading());
                cStmt.setString("courseModuleContentText", courseContent.GetTextContent());

                boolean hasResult = cStmt.execute();
                while(hasResult){
                    connection.commit();;
                  return courseContent.GetContentId();

                }
                throw new SQLException("Could not update with new values courseModuleContent");
            }

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public TextCourseContent Load(int courseContentId) throws SQLException {
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module_content WHERE courseModuleContentId = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, courseContentId);
            ResultSet resultSet = statement.executeQuery();

            String course_module_content_name = "";
            String course_module_content_text = "";

            while (resultSet.next()) {
                course_module_content_name = resultSet.getString("CourseModuleContentName");
                course_module_content_text = resultSet.getString("course_module_content_text");
            }

            return courseContentFactory.CreateTextCourseContent(course_module_content_name, course_module_content_text);

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public ArrayList<CourseContent> LoadAllContentsInModule(int courseModuleId) throws SQLException {

        ArrayList<CourseContent> courseContents = new ArrayList<>();
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();


        try (Connection connection = database.getConnection()) {
            CallableStatement cStmt = connection.prepareCall("{call GetAllContentsInModule (?)}");
            cStmt.setInt("courseModuleId", courseModuleId);

            ResultSet resultSet = cStmt.getResultSet();
            while(resultSet.next()){
                String courseModuleHeading = resultSet.getString("courseModuleContentName");
                String courseModuleText = resultSet.getString("courseModuleContentText");
                int courseModuleContentId = resultSet.getInt("courseModuleContentId");
                CourseContent courseContent = courseContentFactory.CreateTextCourseContent(courseModuleContentId,courseModuleHeading, courseModuleText);
                courseContents.add(courseContent);
            }
            connection.commit();
            return courseContents;

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}
