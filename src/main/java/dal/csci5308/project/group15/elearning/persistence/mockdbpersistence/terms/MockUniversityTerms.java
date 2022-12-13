package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.terms;

import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.UniversityTerms;
import dal.csci5308.project.group15.elearning.persistence.terms.IUniversityTermsPersistence;

import java.sql.Date;
import java.util.ArrayList;

public class MockUniversityTerms implements IUniversityTermsPersistence {
    @Override
    public void save(UniversityTerms universityTerms) {

    }

    @Override
    public ArrayList<IUniversityTerms> loadTermsAfterCurrentDate(Date currentDate) {
        return null;
    }

    @Override
    public ArrayList<IUniversityTerms> loadOpenForRegistrationTerms(Date currentDate) {
        return null;
    }

    @Override
    public ArrayList<IUniversityTerms> loadCurrentTerm(Date currentDate) {
        return null;
    }

    public IUniversityTerms loadTermByTermId(String termId){
        return null;
    }
}
