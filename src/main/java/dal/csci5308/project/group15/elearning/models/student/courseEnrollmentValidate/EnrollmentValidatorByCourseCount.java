package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate;

import dal.csci5308.project.group15.elearning.factory.properties.IPropertiesFactory;
import dal.csci5308.project.group15.elearning.factory.properties.PropertiesFactory;
import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.IValidationState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationFailedState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationSuccessState;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;

public class EnrollmentValidatorByCourseCount extends EnrollmentValidatorTemplate {

    IPropertiesFactory propertiesFactory = PropertiesFactory.instance();
    @Override
    IValidationState validateStudentEligibility(IStudentCourseEnrollment studentCourseEnrollment, IValidationState validationState) {
        Integer studentEnrolledCourseCount = studentCourseEnrollment.loadStudentCourseCountByTerm(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentCourseEnrollment.getStudentNumber(), studentCourseEnrollment.getCourseTerm());
        Integer maxCourseCountPerTerm = Integer.valueOf(propertiesFactory.makeSqlProperties().getPropertiesMap().get("MAX_COURSE_COUNT_PER_TERM"));

        if (studentEnrolledCourseCount >= maxCourseCountPerTerm) {
            validationState = new ValidationFailedState();
            validationState.setValidationResult("Cannot Register! Student has already enrolled (" + studentEnrolledCourseCount + ") in the maximum allowed (" + maxCourseCountPerTerm + ") courses!");
            return validationState;
        }
        return new ValidationSuccessState();
    }
}
