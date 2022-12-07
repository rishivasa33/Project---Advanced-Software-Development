package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBCourseTextCourseContentPersistence;


public class TextContentPersistenceSingleton {
    private static Database database_;
    private  static MockDBCourseTextCourseContentPersistence mockDBCourseTextContentPersistenceInstance;


    private static MockDBCourseTextCourseContentPersistence CreateMockDBTextCourseContentPersistence(){
        return  new MockDBCourseTextCourseContentPersistence();
    }

    private TextContentPersistenceSingleton(){
        database_ = null;
        mockDBCourseTextContentPersistenceInstance = null;

    }



    public static TextCourseContentPersistence GetMockDBTextCourseContentPersistence(){
        if(mockDBCourseTextContentPersistenceInstance == null){
           mockDBCourseTextContentPersistenceInstance = CreateMockDBTextCourseContentPersistence();
        }
        return mockDBCourseTextContentPersistenceInstance;
    }

    public static TextCourseContentPersistence GetTextCourseContentPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBTextCourseContentPersistence();
        }
        else{
            return null;
        }
    }
}
