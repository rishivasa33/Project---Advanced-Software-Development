package dal.csci5308.project.group15.elearning.persistence.student;

import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student.MockStudentDetails;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student.MySqlStudentDetails;

public class StudentDetailsSingleton {

    private static MySqlStudentDetails mySqlStudentDetails_instance;
    private static MockStudentDetails mockStudentDetails_instance;

    private StudentDetailsSingleton() {
        this.mockStudentDetails_instance = null;
        this.mySqlStudentDetails_instance = null;
    }

    private static MySqlStudentDetails CreateMySqlStudentDetailsPersistence() {
        return new MySqlStudentDetails();
    }

    private static MockStudentDetails CreateMockStudentDetailsPersistence() {
        return new MockStudentDetails();
    }

    public static MySqlStudentDetails GetMySqlStudentDetailsInstance() {
        if (mySqlStudentDetails_instance == null) {
            mySqlStudentDetails_instance = CreateMySqlStudentDetailsPersistence();
        }
        return mySqlStudentDetails_instance;
    }

    public static MockStudentDetails GetMockStudentDetailsInstance() {
        if (mockStudentDetails_instance == null) {
            mockStudentDetails_instance = CreateMockStudentDetailsPersistence();
        }
        return mockStudentDetails_instance;
    }
}
