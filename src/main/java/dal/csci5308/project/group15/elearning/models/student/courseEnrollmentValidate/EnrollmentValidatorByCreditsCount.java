package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate;

import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.IValidationState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationFailedState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationSuccessState;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;

public class EnrollmentValidatorByCreditsCount extends EnrollmentValidatorTemplate {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();

    @Override
    IValidationState validateStudentEligibility(IStudentCourseEnrollment studentCourseEnrollment, IValidationState validationState) {
        Integer studentEnrolledCreditsCount = studentCourseEnrollment.loadStudentCreditCountByTerm(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentCourseEnrollment.getStudentNumber(), studentCourseEnrollment.getCourseTerm());
        Integer maxCreditsPerTerm = Integer.valueOf(propertiesFactory.makeSqlProperties().getPropertiesMap().get("MAX_CREDITS_COUNT_PER_TERM"));

        if (studentEnrolledCreditsCount >= maxCreditsPerTerm) {
            validationState = new ValidationFailedState();
            validationState.setValidationResult("Cannot Register! Student has already enrolled (" + studentEnrolledCreditsCount + ") in the maximum allowed (" + maxCreditsPerTerm + ") credits!");
            return validationState;
        }
        return new ValidationSuccessState();
    }
}
