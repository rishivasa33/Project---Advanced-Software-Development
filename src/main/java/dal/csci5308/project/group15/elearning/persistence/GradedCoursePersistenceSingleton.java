package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBGradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlGradedCoursePersistence;


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

    public static MySqlGradedCoursePersistence GetMySqlGradedCoursePersistenceInstance(){
        if(database_ == null){
            database_ = new Database();
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
}
