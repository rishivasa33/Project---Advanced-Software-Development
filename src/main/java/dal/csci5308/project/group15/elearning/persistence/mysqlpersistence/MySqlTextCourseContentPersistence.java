package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.persistence.TextCourseContentPersistence;

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
//                String sql_query = "insert into course_module_content(course_module_content_name, course_module_content_text, course_module_id) values(? , ?, ?);";
//
//
//                PreparedStatement preparedStatement = connection.prepareStatement(sql_query, Statement.RETURN_GENERATED_KEYS);
//                preparedStatement.setString(1, courseContent.GetContentHeading());
//                preparedStatement.setString(2, courseContent.GetTextContent());
//                preparedStatement.setInt(3, courseModuleId);
//                int rows_modified = preparedStatement.executeUpdate();
//                connection.commit();
//                ResultSet rs = preparedStatement.getGeneratedKeys();
//                rs.next();
//                return rs.getInt(1);
               // CALL `CSCI5308_15_DEVINT`.`InsertTextCourseContent`(<{IN courseModuleId INT}>, <{IN courseModuleContentName VARCHAR(200)}>, <{IN courseModuleContentText VARCHAR(1000)}>, <{OUT courseModuleContentId  INT}>);

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
//                String sql_query = "update course_module_content set course_module_content_name = ?,  course_module_content_text = ?, course_module_id = ?,)  where course_module_content_id = ?";
//                PreparedStatement preparedStatement = connection.prepareStatement(sql_query);
//                preparedStatement.setString(1, courseContent.GetContentHeading());
//                preparedStatement.setString(2, courseContent.GetTextContent());
//                preparedStatement.setInt(3, courseModuleId);
//                preparedStatement.setInt(4, courseContent.GetContentId());
//                int rows_modified = preparedStatement.executeUpdate();
//                connection.commit();

                //CALL `CSCI5308_15_DEVINT`.`UpdateTextCourseContent`(<{IN courseModuleContentId INT}>, <{IN courseModuleId INT}>, <{IN courseModuleContentName VARCHAR(200)}>, <{IN courseModuleContentText VARCHAR(1000)}>);
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
        CourseContentFactory courseContentFactory = new CourseContentFactory();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module_content WHERE course_module_content_id = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, courseContentId);
            ResultSet resultSet = statement.executeQuery();

            String course_module_content_name = "";
            String course_module_content_text = "";

            while (resultSet.next()) {
                course_module_content_name = resultSet.getString("course_module_content_name");
                course_module_content_text = resultSet.getString("course_module_content_text");
            }

            return courseContentFactory.CreateTextCourseContent(course_module_content_name, course_module_content_text);

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public ArrayList<CourseContent> LoadAllContentsInModule(int courseModuleId) throws SQLException {

        ArrayList<CourseContent> courseContents = new ArrayList<>();
        CourseContentFactory courseContentFactory = new CourseContentFactory();

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
