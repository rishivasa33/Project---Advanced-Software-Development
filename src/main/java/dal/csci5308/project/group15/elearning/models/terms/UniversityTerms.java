package dal.csci5308.project.group15.elearning.models.terms;

import dal.csci5308.project.group15.elearning.persistence.terms.IUniversityTermsPersistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class UniversityTerms implements IUniversityTerms {
    private String termID;
    private String termName;
    private Date termStartDate;
    private Date termEndDate;
    private Date registrationStartDate;
    private Date registrationEndDate;


    public UniversityTerms(String termID, String termName, Date termStartDate, Date termEndDate, Date registrationStartDate, Date registrationEndDate) {
        this.termID = termID;
        this.termName = termName;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
    }

    public UniversityTerms(String termID) {
        this.termID = termID;
    }

    public UniversityTerms() {

    }

    public String getTermID() {
        return termID;
    }

    public String getTermName() {
        return termName;
    }

    public Date getTermStartDate() {
        return termStartDate;
    }

    public Date getTermEndDate() {
        return termEndDate;
    }

    public Date getRegistrationStartDate() {
        return registrationStartDate;
    }

    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    @Override
    public void save(IUniversityTermsPersistence iUniversityTermsPersistence) throws SQLException {
        iUniversityTermsPersistence.save(this);
    }

    @Override
    public ArrayList<IUniversityTerms> loadTermsAfterCurrentDate(IUniversityTermsPersistence iUniversityTermsPersistence, Date currentDate) {
        return iUniversityTermsPersistence.loadTermsAfterCurrentDate(currentDate);
    }


    @Override
    public ArrayList<IUniversityTerms> loadOpenForRegistrationTerms(IUniversityTermsPersistence iUniversityTermsPersistence, Date currentDate) {
        return iUniversityTermsPersistence.loadOpenForRegistrationTerms(currentDate);
    }

    @Override
    public ArrayList<IUniversityTerms> loadCurrentTerm(IUniversityTermsPersistence iUniversityTermsPersistence, Date currentDate) {
        return iUniversityTermsPersistence.loadCurrentTerm(currentDate);
    }

    @Override
    public String toString() {
        return "UniversityTerms{" +
                "termID='" + termID + '\'' +
                ", termName='" + termName + '\'' +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                ", registrationStartDate=" + registrationStartDate +
                ", registrationEndDate=" + registrationEndDate +
                '}';
    }

    public IUniversityTerms loadTermByTermId(IUniversityTermsPersistence iUniversityTermsPersistence, String termId) throws SQLException {
        return iUniversityTermsPersistence.loadTermByTermId(termId);
    }
}
