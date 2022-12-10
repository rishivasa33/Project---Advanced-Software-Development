package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.MockDBCreateAssignmentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCreateAssignmentPersistence;


public class CreateAssignmentPersistenceSingleton {

    private static CreateAssignmentPersistence mySqlCoursePersistence_instance_;
    private  static CreateAssignmentPersistence mockDBCoursePersistence_instance_;

    private static CreateAssignmentPersistence CreateMySqlCreateAssignmentPersistence(){
        return new MySqlCreateAssignmentPersistence();
    }
    private static CreateAssignmentPersistence CreateMockDBCreateAssignmentPersistence(){
        return  new MockDBCreateAssignmentPersistence();
    }

    private CreateAssignmentPersistenceSingleton(){

        mockDBCoursePersistence_instance_ = null;
        mySqlCoursePersistence_instance_ = null;
    }

    public static CreateAssignmentPersistence GetMySqlCoursePersistenceInstance() {


        if(mySqlCoursePersistence_instance_ == null){
            mySqlCoursePersistence_instance_ = CreateMySqlCreateAssignmentPersistence();
        }
        return mySqlCoursePersistence_instance_;
    }

    public static CreateAssignmentPersistence GetMockDBCoursePersistenceInstance(){
        if(mockDBCoursePersistence_instance_ == null){
            mockDBCoursePersistence_instance_ = CreateMockDBCreateAssignmentPersistence();
        }
        return mockDBCoursePersistence_instance_;
    }

    public static CreateAssignmentPersistence GetCreateAssignmentPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBCoursePersistenceInstance();
        }
        else{
            return GetMySqlCoursePersistenceInstance();
        }
    }
}


