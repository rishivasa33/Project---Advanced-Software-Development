package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBCourseByTermPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlCourseByTermPersistence;

public class CourseByTermPersistenceSingleton {
    private static Database database_;
    private static MySqlCourseByTermPersistence mySqlCourseInstancePersistenceInstance_;
    private  static MockDBCourseByTermPersistence mockDBCourseInstancePersistenceInstance_;

    private static MySqlCourseByTermPersistence CreateMySqlCoursePersistence(){
        return  new MySqlCourseByTermPersistence(database_);
    }
    private static MockDBCourseByTermPersistence CreateMockDBCoursePersistence(){
        return  new MockDBCourseByTermPersistence();
    }

    private CourseByTermPersistenceSingleton(){
        database_ = null;
        mockDBCourseInstancePersistenceInstance_ = null;
        mySqlCourseInstancePersistenceInstance_ = null;
    }

    public static CourseByTermPersistence GetMySqlCourseInstancePersistenceInstance() {
        database_ = Database.instance();

        if(mySqlCourseInstancePersistenceInstance_ == null){
            mySqlCourseInstancePersistenceInstance_ = CreateMySqlCoursePersistence();
        }
        return mySqlCourseInstancePersistenceInstance_;
    }

    public static CourseByTermPersistence GetMockDBCourseInstancePersistenceInstance(){
        if(mockDBCourseInstancePersistenceInstance_ == null){
           mockDBCourseInstancePersistenceInstance_ = CreateMockDBCoursePersistence();
        }
        return mockDBCourseInstancePersistenceInstance_;
    }

    public static CourseByTermPersistence GetCourseByTermPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBCourseInstancePersistenceInstance();
        }
        else{
            return GetMySqlCourseInstancePersistenceInstance();
        }
    }
}
