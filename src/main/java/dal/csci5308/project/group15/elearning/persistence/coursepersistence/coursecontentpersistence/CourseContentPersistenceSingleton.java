package dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBFileCourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBTextCourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.coursecontentpersistence.MySqlFileCourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.coursecontentpersistence.MySqlTextCourseContentPersistence;


public class CourseContentPersistenceSingleton {
    private static Database database_;
    private  static MockDBTextCourseContentPersistence mockDBCourseTextContentPersistenceInstance;

    private  static MySqlTextCourseContentPersistence mySqlTextContentPersistenceInstance;

    private  static MySqlFileCourseContentPersistence mySqlFileContentPersistenceInstance;

    private  static MockDBFileCourseContentPersistence mockDBFileContentPersistenceInstance;


    private static MockDBTextCourseContentPersistence CreateMockDBTextCourseContentPersistence(){
        return  new MockDBTextCourseContentPersistence();
    }

    private static MySqlTextCourseContentPersistence CreateMySqlTextCourseContentPersistence(){
        return  new MySqlTextCourseContentPersistence();
    }

    private static MySqlFileCourseContentPersistence CreateMySqlFileCourseContentPersistence(){
        return  new MySqlFileCourseContentPersistence();
    }

    private CourseContentPersistenceSingleton(){
        database_ = null;
        mySqlTextContentPersistenceInstance = null;
        mySqlFileContentPersistenceInstance = null;
        mockDBCourseTextContentPersistenceInstance = null;
        mockDBFileContentPersistenceInstance = null;
    }



    public static TextCourseContentPersistence GetMockDBTextCourseContentPersistence(){
        if(mockDBCourseTextContentPersistenceInstance == null){
          mockDBCourseTextContentPersistenceInstance = CreateMockDBTextCourseContentPersistence();
        }
        return mockDBCourseTextContentPersistenceInstance;
    }

    public static FileCourseContentPersistence GetMockDBFileCourseContentPersistence(){
        if(mockDBFileContentPersistenceInstance == null){
            mockDBFileContentPersistenceInstance = new MockDBFileCourseContentPersistence();
        }
        return mockDBFileContentPersistenceInstance;
    }

    public static TextCourseContentPersistence GetMySqlTextCourseContentPersistence(){
        if(mySqlTextContentPersistenceInstance == null){
            mySqlTextContentPersistenceInstance = CreateMySqlTextCourseContentPersistence();
        }
        return mySqlTextContentPersistenceInstance;
    }

    public static FileCourseContentPersistence GetMySqlFileCourseContentPersistence(){
        if(mySqlFileContentPersistenceInstance == null){
            mySqlFileContentPersistenceInstance = CreateMySqlFileCourseContentPersistence();
        }
        return mySqlFileContentPersistenceInstance;
    }

    public static TextCourseContentPersistence GetTextCourseContentPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBTextCourseContentPersistence();
        }
        else{
            return GetMySqlTextCourseContentPersistence();
        }
    }

    public static FileCourseContentPersistence GetFileCourseContentPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBFileCourseContentPersistence();
        }
        else{
            return GetMySqlFileCourseContentPersistence();
        }
    }
}
