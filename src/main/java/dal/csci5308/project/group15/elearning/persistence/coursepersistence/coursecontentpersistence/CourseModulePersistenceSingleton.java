package dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBCourseModulePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlCourseModulePersistence;

public class CourseModulePersistenceSingleton {
    private static Database database_;
    private  static CourseModulePersistence mockDBCourseModulePersistenceInstance;

    private  static CourseModulePersistence mySqlCourseModulePersistenceInstance;




    private static MockDBCourseModulePersistence CreateMockDBCourseModulePersistence(){
        return  new MockDBCourseModulePersistence();
    }

    private static MySqlCourseModulePersistence CreateMySqlCourseModulePersistence(){
        return  new MySqlCourseModulePersistence();
    }

    private CourseModulePersistenceSingleton(){
        database_ = null;
        mockDBCourseModulePersistenceInstance = null;

    }



    public static CourseModulePersistence GetMockDBCourseModulePersistence(){
        if(mockDBCourseModulePersistenceInstance == null){
            mockDBCourseModulePersistenceInstance = CreateMockDBCourseModulePersistence();
        }
        return mockDBCourseModulePersistenceInstance;
    }

    public static CourseModulePersistence GetMySqlCourseModulePersistence(){
        if(mySqlCourseModulePersistenceInstance == null){
           mySqlCourseModulePersistenceInstance = CreateMySqlCourseModulePersistence();
        }
        return mySqlCourseModulePersistenceInstance;
    }

    public static CourseModulePersistence GetCourseModulePersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBCourseModulePersistence();
        }
        else{
            return GetMySqlCourseModulePersistence();
        }
    }
}
