package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.coursecontentpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.FileCourseContentPersistence;

import java.sql.*;

public class MySqlFileCourseContentPersistence implements FileCourseContentPersistence {
    private Database database;
    public MySqlFileCourseContentPersistence() {
        database = Database.instance();
    }

    public int Save(FileCourseContent pdfFileCourseContent, int courseModuleId) throws SQLException {

        try (Connection connection = database.getConnection()) {
            if(pdfFileCourseContent.GetContentId() == null) {
                String sql = "CALL InsertFileCourseContent (?, ?, ?, ?);";
                CallableStatement cStmt = connection.prepareCall(sql);
                cStmt.setInt("courseModuleId", courseModuleId);
                cStmt.setString("courseModuleContentName", pdfFileCourseContent.GetContentHeading());
                cStmt.setString("courseModuleContentFilePath", pdfFileCourseContent.GetFilePath());
                String fileType = "PDF";
                cStmt.setString("courseModuleContentFileType", fileType);

                cStmt.execute();

                String sql_2 = "CALL InsertCourseContentGetLastInsertedId()";
                CallableStatement cStmt_2 = connection.prepareCall(sql_2);
                boolean hasResult = cStmt_2.execute();
                while(hasResult){
                    ResultSet resultSet = cStmt_2.getResultSet();
                    resultSet.next();
                    int courseModuleContentId = resultSet.getInt(1);
                    connection.commit();
                    return courseModuleContentId;

                }
                throw new SQLException("Could not generate courseModuleContentId");
            }
            else {

                String fileType = "PDF";

                //CALL `CSCI5308_15_DEVINT`.`UpdateFileCourseContent`(<{IN courseModuleId INT}>, <{IN courseModuleContentName VARCHAR(200)}>, <{IN courseModuleContentFilePath VARCHAR(1000)}>, <{IN courseModuleContentFileType VARCHAR(100)}>, <{IN courseModuleContentId  INT}>);
                CallableStatement cStmt = connection.prepareCall("{call UpdateTextCourseContent (?, ?,  ?, ?, ?)}");
                cStmt.setInt("courseModuleContentId", pdfFileCourseContent.GetContentId());
                cStmt.setInt("courseModuleId", courseModuleId);
                cStmt.setString("courseModuleContentName", pdfFileCourseContent.GetContentHeading());
                cStmt.setString("courseModuleContentFilePath", pdfFileCourseContent.GetFilePath());
                cStmt.setString("courseModuleContentFileType", fileType);


                boolean hasResult = cStmt.execute();
                while(hasResult){
                    connection.commit();;
                    return pdfFileCourseContent.GetContentId();

                }
                throw new SQLException("Could not update with new values courseModuleContent");
            }

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }

    public FileCourseContent Load(int pdfFileCourseContentId) throws SQLException {
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

        try (Connection connection = database.getConnection()) {
            String sql_query = "SELECT * FROM course_module_content WHERE courseModuleContentId = ?;";

            PreparedStatement statement = connection.prepareStatement(sql_query);
            statement.setInt(1, pdfFileCourseContentId);
            ResultSet resultSet = statement.executeQuery();

            String course_module_content_name = "";
            String course_module_content_file_path = "";

            while (resultSet.next()) {
                course_module_content_name = resultSet.getString("courseModuleContentName");
                course_module_content_file_path = resultSet.getString("courseModuleContentFilePath");
            }

            return courseContentFactory.CreateFileCourseContent(course_module_content_name, course_module_content_file_path);

        } catch (SQLException sqlException) {
            throw sqlException;
        }
    }
}
