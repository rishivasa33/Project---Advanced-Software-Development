package dal.csci5308.project.group15.elearning.persistence.student;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student.MockStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.student.MySqlStudentCourseEnrollment;

public class StudentCourseEnrollmentPersistenceSingleton {

    private static Database database;
    private static MySqlStudentCourseEnrollment mySqlStudentCourseEnrollment_instance;
    private static MockStudentCourseEnrollment mockStudentCourseEnrollment_instance;

    private StudentCourseEnrollmentPersistenceSingleton() {
        this.mockStudentCourseEnrollment_instance = null;
        this.mySqlStudentCourseEnrollment_instance = null;
    }

    private static MySqlStudentCourseEnrollment CreateMySqlStudentCourseEnrollmentPersistence() {
        return new MySqlStudentCourseEnrollment();
    }

    private static MockStudentCourseEnrollment CreateMockStudentCourseEnrollmentPersistence() {
        return new MockStudentCourseEnrollment();
    }

    public static MySqlStudentCourseEnrollment GetMySqlStudentCourseEnrollmentPersistenceInstance() {
        if (database == null) {
            database = Database.instance();
        }
        if (mySqlStudentCourseEnrollment_instance == null) {
            mySqlStudentCourseEnrollment_instance = CreateMySqlStudentCourseEnrollmentPersistence();
        }
        return mySqlStudentCourseEnrollment_instance;
    }

    public static MockStudentCourseEnrollment GetMockDBStudentCourseEnrollmentPersistenceInstance() {
        if (mockStudentCourseEnrollment_instance == null) {
            mockStudentCourseEnrollment_instance = CreateMockStudentCourseEnrollmentPersistence();
        }
        return mockStudentCourseEnrollment_instance;
    }
}
