package dal.csci5308.project.group15.elearning.models.terms;

import java.sql.Date;

public interface IUniversityTermsFactory {
    UniversityTerms createUniversityTermsInstance(String termID, String termName, Date termStartDate, Date termEndDate, Date registrationStartDate, Date registrationEndDate);

    UniversityTerms createUniversityTermsInstanceForLoadByID(String termID);

    UniversityTerms createEmptyUniversityTermsInstance();
}
