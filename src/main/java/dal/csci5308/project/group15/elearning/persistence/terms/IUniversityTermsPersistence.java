package dal.csci5308.project.group15.elearning.persistence.terms;

import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IUniversityTermsPersistence {
    void save(UniversityTerms universityTerms);

    ArrayList<IUniversityTerms> loadTermsAfterCurrentDate(Date currentDate);

    ArrayList<IUniversityTerms> loadOpenForRegistrationTerms(Date currentDate);

    ArrayList<IUniversityTerms> loadCurrentTerm(Date currentDate);

    IUniversityTerms loadTermByTermId(String termId) throws SQLException;
}
