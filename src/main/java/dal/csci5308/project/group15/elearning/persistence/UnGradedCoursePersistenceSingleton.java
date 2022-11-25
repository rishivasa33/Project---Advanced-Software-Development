package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBUngradedCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlUnGradedCoursePersistence;

public class UnGradedCoursePersistenceSingleton {
    private static Database database_;
    private static MySqlUnGradedCoursePersistence mySqlUnGradedCoursePersistence_instance;
    private  static MockDBUngradedCoursePersistence mockDBUnGradedCoursePersistence_instance;

    private static MySqlUnGradedCoursePersistence CreateMySqlUnGradedCoursePersistence(){
        return  new MySqlUnGradedCoursePersistence((MySqlCoursePersistence) CoursePersistenceSingleton.GetMySqlCoursePersistenceInstance(),  database_);
    }
    private static MockDBUngradedCoursePersistence CreateMockDBUnGradedCoursePersistence(){
        return  new MockDBUngradedCoursePersistence();
    }

    private UnGradedCoursePersistenceSingleton(){
        database_ = null;
        mockDBUnGradedCoursePersistence_instance = null;
        mySqlUnGradedCoursePersistence_instance = null;
    }

    public static UnGradedCoursePersistence GetMySqlUnGradedCoursePersistenceInstance(){
        if(database_ == null){
            database_ = new Database();
        }
        if(mySqlUnGradedCoursePersistence_instance == null){
            mySqlUnGradedCoursePersistence_instance = CreateMySqlUnGradedCoursePersistence();
        }
        return mySqlUnGradedCoursePersistence_instance;
    }

    public static UnGradedCoursePersistence GetMockDBUnGradedCoursePersistenceInstance(){
        if(mockDBUnGradedCoursePersistence_instance == null){
            mockDBUnGradedCoursePersistence_instance = CreateMockDBUnGradedCoursePersistence();
        }
        return mockDBUnGradedCoursePersistence_instance;
    }
}
