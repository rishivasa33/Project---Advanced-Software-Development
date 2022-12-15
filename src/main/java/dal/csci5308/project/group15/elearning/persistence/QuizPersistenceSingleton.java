package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.quiz.MockDBQuizPersistence;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.quiz.MySqlQuizPersistence;

public class QuizPersistenceSingleton {

    private static QuizPersistence mySqlQuizPersistence_instance_;
    private static QuizPersistence mockDBQuizPersistence_instance_;

    private QuizPersistenceSingleton() {
        mockDBQuizPersistence_instance_ = null;
        mySqlQuizPersistence_instance_ = null;
    }

    private static QuizPersistence CreateMySqlQuizPersistence() {
        return new MySqlQuizPersistence();
    }

    private static QuizPersistence CreateMockDBQuizPersistence() {
        return new MockDBQuizPersistence();
    }

    public static QuizPersistence GetMySqlQuizPersistenceInstance() {

        if (mySqlQuizPersistence_instance_ == null) {
            mySqlQuizPersistence_instance_ = CreateMySqlQuizPersistence();
        }
        return mySqlQuizPersistence_instance_;
    }

    public static QuizPersistence GetMockDBQuizPersistenceInstance() {
        if (mockDBQuizPersistence_instance_ == null) {
            mockDBQuizPersistence_instance_ = CreateMockDBQuizPersistence();
        }
        return mockDBQuizPersistence_instance_;
    }

    public static QuizPersistence GetQuizPersistence() {
        String is_test_mode = System.getenv().getOrDefault("IS_TEST_MODE", null);
        if (is_test_mode != null && is_test_mode.equals("TRUE")) {
            return GetMockDBQuizPersistenceInstance();
        } else {
            return GetMySqlQuizPersistenceInstance();
        }
    }
}
