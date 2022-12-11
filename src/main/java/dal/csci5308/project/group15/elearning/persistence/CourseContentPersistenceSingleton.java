package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBTextCourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlTextCourseContentPersistence;


public class CourseContentPersistenceSingleton {
    private static Database database_;
    private  static MockDBTextCourseContentPersistence mockDBCourseTextContentPersistenceInstance;

    private  static MySqlTextCourseContentPersistence mySqlTextContentPersistenceInstance;


    private static MockDBTextCourseContentPersistence CreateMockDBTextCourseContentPersistence(){
        return  new MockDBTextCourseContentPersistence();
    }

    private static MySqlTextCourseContentPersistence CreateMySqlTextCourseContentPersistence(){
        return  new MySqlTextCourseContentPersistence();
    }

    private CourseContentPersistenceSingleton(){
        database_ = null;
        mySqlTextContentPersistenceInstance = null;
        mockDBCourseTextContentPersistenceInstance = null;
    }



    public static TextCourseContentPersistence GetMockDBTextCourseContentPersistence(){
        if(mockDBCourseTextContentPersistenceInstance == null){
          mockDBCourseTextContentPersistenceInstance = CreateMockDBTextCourseContentPersistence();
        }
        return mockDBCourseTextContentPersistenceInstance;
    }

    public static TextCourseContentPersistence GetMySqlTextCourseContentPersistence(){
        if(mySqlTextContentPersistenceInstance == null){
            mySqlTextContentPersistenceInstance = CreateMySqlTextCourseContentPersistence();
        }
        return mySqlTextContentPersistenceInstance;
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
}
