package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlCoursePersistence;

public class CoursePersistenceSingleton {
    private static Database database_;
    private static  MySqlCoursePersistence mySqlCoursePersistence_instance_;
    private  static MockDBCoursePersistence mockDBCoursePersistence_instance_;

    private static MySqlCoursePersistence CreateMySqlCoursePersistence(){
        return  new MySqlCoursePersistence(database_);
    }
    private static MockDBCoursePersistence CreateMockDBCoursePersistence(){
        return  new MockDBCoursePersistence();
    }

    private CoursePersistenceSingleton(){
        database_ = null;
        mockDBCoursePersistence_instance_ = null;
        mySqlCoursePersistence_instance_ = null;
    }

    public static CoursePersistence GetMySqlCoursePersistenceInstance() {
        database_ = Database.instance();

        if(mySqlCoursePersistence_instance_ == null){
            mySqlCoursePersistence_instance_ = CreateMySqlCoursePersistence();
        }
        return mySqlCoursePersistence_instance_;
    }

    public static CoursePersistence GetMockDBCoursePersistenceInstance(){
        if(mockDBCoursePersistence_instance_ == null){
            mockDBCoursePersistence_instance_ = CreateMockDBCoursePersistence();
        }
        return mockDBCoursePersistence_instance_;
    }

    public static CoursePersistence GetCoursePersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBCoursePersistenceInstance();
        }
        else{
            return GetMySqlCoursePersistenceInstance();
        }
    }
}
