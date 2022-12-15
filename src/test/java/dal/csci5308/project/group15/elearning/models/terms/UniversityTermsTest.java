package dal.csci5308.project.group15.elearning.models.terms;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.persistence.terms.IUniversityTermsPersistence;
import dal.csci5308.project.group15.elearning.persistence.terms.UniversityTermsSingleton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UniversityTermsTest {


    @Test
    void createUniversityTermsInstanceTest() {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createEmptyUniversityTermsInstance();
        assertNull(universityTerms.getTermID());
        assertNull(universityTerms.getTermName());
        assertNull(universityTerms.getTermStartDate());
        assertNull(universityTerms.getTermEndDate());
        assertNull(universityTerms.getRegistrationStartDate());
        assertNull(universityTerms.getRegistrationEndDate());
    }

    @Test
    void createUniversityTermsInstanceForLoadByIDTest() {
        String termID = "W23";
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstanceForLoadByID(termID);
        assertEquals(universityTerms.getTermID(), termID);
        assertNull(universityTerms.getTermName());
        assertNull(universityTerms.getTermStartDate());
        assertNull(universityTerms.getTermEndDate());
        assertNull(universityTerms.getRegistrationStartDate());
        assertNull(universityTerms.getRegistrationEndDate());
    }

    @Test
    void createEmptyUniversityTermsInstanceTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getTermID(), termID);
        assertEquals(universityTerms.getTermName(), termName);
        assertEquals(universityTerms.getTermStartDate(), termStartDate);
        assertEquals(universityTerms.getTermEndDate(), termEndDate);
        assertEquals(universityTerms.getRegistrationStartDate(), registrationStartDate);
        assertEquals(universityTerms.getRegistrationEndDate(), registrationEndDate);
    }

    @Test
    void getTermIDTest() {
        String termID = "W23";
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstanceForLoadByID(termID);

        assertEquals(universityTerms.getTermID(), termID);
    }

    @Test
    void getTermNameTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getTermName(), termName);
    }

    @Test
    void getTermStartDateTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getTermStartDate(), termStartDate);
    }

    @Test
    void getTermEndDateTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getTermEndDate(), termEndDate);
    }

    @Test
    void getRegistrationStartDateTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getRegistrationStartDate(), registrationStartDate);
    }

    @Test
    void getRegistrationEndDateTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);

        assertEquals(universityTerms.getRegistrationEndDate(), registrationEndDate);
    }

    @Test
    void saveTest() {
        String termID = "W23";
        String termName = "Winter 2023";
        Date termStartDate = Date.valueOf("2022-08-01");
        Date termEndDate = Date.valueOf("2022-12-31");
        Date registrationStartDate = Date.valueOf("2022-07-01");
        Date registrationEndDate = Date.valueOf("2022-08-15");
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerms = universityTermsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);
        IUniversityTermsPersistence universityTermsPersistence = UniversityTermsSingleton.GetMockDBUniversityTermsPersistenceInstance();

        try {
            universityTerms.save(universityTermsPersistence);
            Assertions.assertTrue(true);
        } catch (SQLException e) {
            Assertions.fail();
        }

    }

    @Test
    void loadTermsAfterCurrentDateTest() {
        Date currentDate = new Date(System.currentTimeMillis());

        ArrayList<IUniversityTerms> universityTerms = new ArrayList<>();
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerm = universityTermsFactory.createEmptyUniversityTermsInstance();
        IUniversityTermsPersistence universityTermsPersistence = UniversityTermsSingleton.GetMockDBUniversityTermsPersistenceInstance();

        universityTerms = universityTerm.loadTermsAfterCurrentDate(universityTermsPersistence, currentDate);

        assertEquals(universityTerms.get(0).getTermID(), "F22");
        assertEquals(universityTerms.get(0).getTermName(), "Fall 2022");
        assertEquals(universityTerms.get(0).getTermStartDate(), Date.valueOf("2022-08-01"));
        assertEquals(universityTerms.get(0).getTermEndDate(), Date.valueOf("2022-12-31"));
        assertEquals(universityTerms.get(0).getRegistrationStartDate(), Date.valueOf("2022-07-01"));
        assertEquals(universityTerms.get(0).getRegistrationEndDate(), Date.valueOf("2022-08-15"));

        assertEquals(universityTerms.get(1).getTermID(), "W23");
        assertEquals(universityTerms.get(1).getTermName(), "Winter 2023");
        assertEquals(universityTerms.get(1).getTermStartDate(), Date.valueOf("2023-01-01"));
        assertEquals(universityTerms.get(1).getTermEndDate(), Date.valueOf("2023-05-01"));
        assertEquals(universityTerms.get(1).getRegistrationStartDate(), Date.valueOf("2022-12-20"));
        assertEquals(universityTerms.get(1).getRegistrationEndDate(), Date.valueOf("2023-01-15"));

    }

    @Test
    void loadOpenForRegistrationTermsTest() {
        Date currentDate = new Date(System.currentTimeMillis());

        ArrayList<IUniversityTerms> universityTerms = new ArrayList<>();
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerm = universityTermsFactory.createEmptyUniversityTermsInstance();
        IUniversityTermsPersistence universityTermsPersistence = UniversityTermsSingleton.GetMockDBUniversityTermsPersistenceInstance();

        universityTerms = universityTerm.loadOpenForRegistrationTerms(universityTermsPersistence, currentDate);

        assertEquals(universityTerms.get(0).getTermID(), "W23");
        assertEquals(universityTerms.get(0).getTermName(), "Winter 2023");
        assertEquals(universityTerms.get(0).getTermStartDate(), Date.valueOf("2023-01-01"));
        assertEquals(universityTerms.get(0).getTermEndDate(), Date.valueOf("2023-05-01"));
        assertEquals(universityTerms.get(0).getRegistrationStartDate(), Date.valueOf("2022-12-20"));
        assertEquals(universityTerms.get(0).getRegistrationEndDate(), Date.valueOf("2023-01-15"));

        assertEquals(universityTerms.get(1).getTermID(), "S23");
        assertEquals(universityTerms.get(1).getTermName(), "Summer 2023");
        assertEquals(universityTerms.get(1).getTermStartDate(), Date.valueOf("2023-01-01"));
        assertEquals(universityTerms.get(1).getTermEndDate(), Date.valueOf("2023-05-01"));
        assertEquals(universityTerms.get(1).getRegistrationStartDate(), Date.valueOf("2022-12-20"));
        assertEquals(universityTerms.get(1).getRegistrationEndDate(), Date.valueOf("2023-01-15"));

    }

    @Test
    void loadCurrentTermTest() {
        Date currentDate = new Date(System.currentTimeMillis());

        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerm = universityTermsFactory.createEmptyUniversityTermsInstance();
        IUniversityTermsPersistence universityTermsPersistence = UniversityTermsSingleton.GetMockDBUniversityTermsPersistenceInstance();

        universityTerm = universityTerm.loadCurrentTerm(universityTermsPersistence, currentDate);

        assertEquals(universityTerm.getTermID(), "F22");
        assertEquals(universityTerm.getTermName(), "Fall 2022");
        assertEquals(universityTerm.getTermStartDate(), Date.valueOf("2022-08-01"));
        assertEquals(universityTerm.getTermEndDate(), Date.valueOf("2022-12-31"));
        assertEquals(universityTerm.getRegistrationStartDate(), Date.valueOf("2022-07-01"));
        assertEquals(universityTerm.getRegistrationEndDate(), Date.valueOf("2022-08-15"));

    }

    @Test
    void loadTermByTermIdTest() {
        String termID = "W23";
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms universityTerm = universityTermsFactory.createEmptyUniversityTermsInstance();
        IUniversityTermsPersistence universityTermsPersistence = UniversityTermsSingleton.GetMockDBUniversityTermsPersistenceInstance();

        try {
            universityTerm = universityTerm.loadTermByTermId(universityTermsPersistence, termID);
            assertEquals(universityTerm.getTermID(), "W23");
            assertEquals(universityTerm.getTermName(), "Winter 2023");
            assertEquals(universityTerm.getTermStartDate(), Date.valueOf("2023-01-01"));
            assertEquals(universityTerm.getTermEndDate(), Date.valueOf("2023-05-01"));
            assertEquals(universityTerm.getRegistrationStartDate(), Date.valueOf("2022-12-20"));
            assertEquals(universityTerm.getRegistrationEndDate(), Date.valueOf("2023-01-15"));
        } catch (SQLException e) {
            Assertions.fail();
        }
    }
}