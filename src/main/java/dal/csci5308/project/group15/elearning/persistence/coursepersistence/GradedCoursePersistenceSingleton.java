package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence.MockDBGradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.coursepersistence.MySqlGradedCoursePersistence;


public class GradedCoursePersistenceSingleton {
    private static Database database_;
    private static MySqlGradedCoursePersistence mySqlGradedCoursePersistence_instance;
    private  static MockDBGradedCoursePersistence mockDBGradedCoursePersistence_instance;

    private static MySqlGradedCoursePersistence CreateMySqlGradedCoursePersistence(){
        return  new MySqlGradedCoursePersistence((MySqlCoursePersistence) CoursePersistenceSingleton.GetMySqlCoursePersistenceInstance(), database_);
    }
    private static MockDBGradedCoursePersistence CreateMockDBCoursePersistence(){
        return  new MockDBGradedCoursePersistence();
    }

    private GradedCoursePersistenceSingleton(){
        database_ = null;
        mockDBGradedCoursePersistence_instance = null;
        mySqlGradedCoursePersistence_instance = null;
    }

    public static MySqlGradedCoursePersistence GetMySqlGradedCoursePersistenceInstance() {
        if(database_ == null){
            database_ = Database.instance();
        }
        if(mySqlGradedCoursePersistence_instance == null){
            mySqlGradedCoursePersistence_instance = CreateMySqlGradedCoursePersistence();
        }
        return mySqlGradedCoursePersistence_instance;
    }

    public static GradedCoursePersistence GetMockDBGradedCoursePersistenceInstance(){
        if(mockDBGradedCoursePersistence_instance == null){
            mockDBGradedCoursePersistence_instance = CreateMockDBCoursePersistence();
        }
        return mockDBGradedCoursePersistence_instance;
    }

    public static GradedCoursePersistence GetGradedCoursePersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBGradedCoursePersistenceInstance();
        }
        else{
            return GetMySqlGradedCoursePersistenceInstance();
        }
    }
}
