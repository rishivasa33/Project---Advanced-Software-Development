package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.assignment.MockDBCreateAssignmentPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.assignment.MySqlCreateAssignmentPersistence;


public class CreateAssignmentPersistenceSingleton {

    private static CreateAssignmentPersistence mySqlCreateAssignmentPersistence_instance_;
    private  static CreateAssignmentPersistence mockDBCreateAssignmentPersistence_instance_;


    private static CreateAssignmentPersistence CreateMySqlCreateAssignmentPersistence(){
        return new MySqlCreateAssignmentPersistence();
    }
    private static CreateAssignmentPersistence CreateMockDBCreateAssignmentPersistence(){
        return  new MockDBCreateAssignmentPersistence();
    }

    private CreateAssignmentPersistenceSingleton(){

        mockDBCreateAssignmentPersistence_instance_ = null;
        mySqlCreateAssignmentPersistence_instance_ = null;
    }

    public static CreateAssignmentPersistence GetMySqlCreateAssignmentPersistenceInstance() {


        if(mySqlCreateAssignmentPersistence_instance_ == null){
            mySqlCreateAssignmentPersistence_instance_ = CreateMySqlCreateAssignmentPersistence();
        }
        return mySqlCreateAssignmentPersistence_instance_;
    }

    public static CreateAssignmentPersistence GetMockDBCreateAssignmentPersistenceInstance(){
        if(mockDBCreateAssignmentPersistence_instance_ == null){
            mockDBCreateAssignmentPersistence_instance_ = CreateMockDBCreateAssignmentPersistence();
        }
        return mockDBCreateAssignmentPersistence_instance_;
    }

    public static CreateAssignmentPersistence GetCreateAssignmentPersistence(){
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if(is_test_mode != null && is_test_mode.equals("TRUE")){
            return GetMockDBCreateAssignmentPersistenceInstance();
        }
        else{
            return GetMySqlCreateAssignmentPersistenceInstance();
        }
    }
}


