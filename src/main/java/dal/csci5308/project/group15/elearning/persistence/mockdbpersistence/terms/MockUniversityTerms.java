package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.terms;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTerms;
import dal.csci5308.project.group15.elearning.models.terms.IUniversityTermsFactory;
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
        ArrayList<IUniversityTerms> universityTerms = new ArrayList<>();
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();

        IUniversityTerms term = universityTermsFactory.createUniversityTermsInstance("F22", "Fall 2022", Date.valueOf("2022-08-01"), Date.valueOf("2022-12-31"), Date.valueOf("2022-07-01"), Date.valueOf("2022-08-15"));
        universityTerms.add(term);

        term = universityTermsFactory.createUniversityTermsInstance("W23", "Winter 2023", Date.valueOf("2023-01-01"), Date.valueOf("2023-05-01"), Date.valueOf("2022-12-20"), Date.valueOf("2023-01-15"));
        universityTerms.add(term);

        return universityTerms;
    }

    @Override
    public ArrayList<IUniversityTerms> loadOpenForRegistrationTerms(Date currentDate) {
        ArrayList<IUniversityTerms> universityTerms = new ArrayList<>();
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();

        IUniversityTerms term = universityTermsFactory.createUniversityTermsInstance("W23", "Winter 2023", Date.valueOf("2023-01-01"), Date.valueOf("2023-05-01"), Date.valueOf("2022-12-20"), Date.valueOf("2023-01-15"));
        universityTerms.add(term);

        term = universityTermsFactory.createUniversityTermsInstance("S23", "Summer 2023", Date.valueOf("2023-01-01"), Date.valueOf("2023-05-01"), Date.valueOf("2022-12-20"), Date.valueOf("2023-01-15"));
        universityTerms.add(term);

        return universityTerms;
    }

    @Override
    public IUniversityTerms loadCurrentTerm(Date currentDate) {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms term = universityTermsFactory.createUniversityTermsInstance("F22", "Fall 2022", Date.valueOf("2022-08-01"), Date.valueOf("2022-12-31"), Date.valueOf("2022-07-01"), Date.valueOf("2022-08-15"));
        return term;
    }

    public IUniversityTerms loadTermByTermId(String termId) {
        IUniversityTermsFactory universityTermsFactory = FactoryFacade.instance().getUniversityTermsFactory();
        IUniversityTerms term = universityTermsFactory.createUniversityTermsInstance(termId, "Winter 2023", Date.valueOf("2023-01-01"), Date.valueOf("2023-05-01"), Date.valueOf("2022-12-20"), Date.valueOf("2023-01-15"));
        return term;
    }
}
