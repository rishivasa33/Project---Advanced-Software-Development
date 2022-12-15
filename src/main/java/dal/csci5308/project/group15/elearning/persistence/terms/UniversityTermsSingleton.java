package dal.csci5308.project.group15.elearning.persistence.terms;

import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.terms.MockUniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.terms.MySqlUniversityTerms;

public class UniversityTermsSingleton {
    private static MySqlUniversityTerms mySqlUniversityTerms_instance;
    private static MockUniversityTerms mockUniversityTerms_instance;

    private UniversityTermsSingleton() {
        this.mockUniversityTerms_instance = null;
        this.mySqlUniversityTerms_instance = null;
    }

    private static MySqlUniversityTerms CreateMySqlUniversityTermsPersistence() {
        return new MySqlUniversityTerms();
    }

    private static MockUniversityTerms CreateMockUniversityTermsPersistence() {
        return new MockUniversityTerms();
    }

    public static MySqlUniversityTerms GetMySqlUniversityTermsPersistenceInstance() {
        if (mySqlUniversityTerms_instance == null) {
            mySqlUniversityTerms_instance = CreateMySqlUniversityTermsPersistence();
        }
        return mySqlUniversityTerms_instance;
    }

    public static MockUniversityTerms GetMockDBUniversityTermsPersistenceInstance() {
        if (mockUniversityTerms_instance == null) {
            mockUniversityTerms_instance = CreateMockUniversityTermsPersistence();
        }
        return mockUniversityTerms_instance;
    }

}
