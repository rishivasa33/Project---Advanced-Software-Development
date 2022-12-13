package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.terms;

import dal.csci5308.project.group15.elearning.database.DatabaseOperations;
import dal.csci5308.project.group15.elearning.database.IDatabaseOperations;
import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.terms.IUniversityTermsPersistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlUniversityTerms implements IUniversityTermsPersistence {

    IDatabaseOperations databaseOperations = DatabaseOperations.instance();
    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();


    @Override
    public void save(UniversityTerms universityTerms) {

    }

    @Override
    public ArrayList<IUniversityTerms> loadTermsAfterCurrentDate(Date currentDate) {
        ArrayList<IUniversityTerms> universityTerms;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_TERMS_GET_AFTER_CURRENT_DATE"), currentDate);

            universityTerms = parseTermFieldsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return universityTerms;
    }

    @Override
    public ArrayList<IUniversityTerms> loadOpenForRegistrationTerms(Date currentDate) {
        ArrayList<IUniversityTerms> universityTerms;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_TERMS_GET_OPEN_FOR_REGISTRATION"), currentDate);

            universityTerms = parseTermFieldsToList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return universityTerms;
    }

    @Override
    public IUniversityTerms loadCurrentTerm(Date currentDate) {
        IUniversityTerms universityTerm;

        Map<String, List<Object>> resultSet;
        try {
            resultSet = databaseOperations.read(
                    propertiesFactory.makeSqlProperties().getPropertiesMap().get("STORED_PROCEDURE_TERMS_GET_CURRENT_TERM"), currentDate);

            universityTerm = parseCurrentTermFields(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return universityTerm;
    }

    private IUniversityTerms parseCurrentTermFields(Map<String, List<Object>> resultSet) {
        IUniversityTermsFactory termsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms currentUniversityTerm = null;
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            String termID = String.valueOf(databaseOperations.getValueAt(resultSet, "term_id", row));
            String termName = String.valueOf(databaseOperations.getValueAt(resultSet, "term_name", row));
            Date termStartDate = (Date) databaseOperations.getValueAt(resultSet, "term_start_date", row);
            Date termEndDate = (Date) databaseOperations.getValueAt(resultSet, "term_end_date", row);
            Date registrationStartDate = (Date) databaseOperations.getValueAt(resultSet, "registration_start_date", row);
            Date registrationEndDate = (Date) databaseOperations.getValueAt(resultSet, "registration_end_date", row);

            currentUniversityTerm = termsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);
        }
        return currentUniversityTerm;
    }

    private ArrayList<IUniversityTerms> parseTermFieldsToList(Map<String, List<Object>> resultSet) {
        IUniversityTermsFactory termsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        ArrayList<IUniversityTerms> universityTerms = new ArrayList<>();
        for (int row = 0; row < databaseOperations.getRowCount(resultSet); row++) {
            String termID = String.valueOf(databaseOperations.getValueAt(resultSet, "term_id", row));
            String termName = String.valueOf(databaseOperations.getValueAt(resultSet, "term_name", row));
            Date termStartDate = (Date) databaseOperations.getValueAt(resultSet, "term_start_date", row);
            Date termEndDate = (Date) databaseOperations.getValueAt(resultSet, "term_end_date", row);
            Date registrationStartDate = (Date) databaseOperations.getValueAt(resultSet, "registration_start_date", row);
            Date registrationEndDate = (Date) databaseOperations.getValueAt(resultSet, "registration_end_date", row);

            IUniversityTerms term = termsFactory.createUniversityTermsInstance(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);
            universityTerms.add(term);
        }
        return universityTerms;
    }
}
