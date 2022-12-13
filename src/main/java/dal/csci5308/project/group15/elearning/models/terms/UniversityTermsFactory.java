package dal.csci5308.project.group15.elearning.models.terms;

import java.sql.Date;

public class UniversityTermsFactory implements IUniversityTermsFactory {
    @Override
    public UniversityTerms createUniversityTermsInstance(String termID, String termName, Date termStartDate, Date termEndDate, Date registrationStartDate, Date registrationEndDate) {
        return new UniversityTerms(termID, termName, termStartDate, termEndDate, registrationStartDate, registrationEndDate);
    }

    @Override
    public UniversityTerms createUniversityTermsInstanceForLoadByID(String termID) {
        return new UniversityTerms(termID);
    }

    @Override
    public UniversityTerms createEmptyUniversityTermsInstance() {
        return new UniversityTerms();
    }
}
