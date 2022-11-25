package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBCoursePersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCoursePersistence;

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

    public static CoursePersistence GetMySqlCoursePersistenceInstance(){
        if(database_ == null){
            database_ = new Database();
        }
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
}
