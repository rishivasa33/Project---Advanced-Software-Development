package dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate;

import dal.csci5308.project.group15.elearning.models.student.IStudentCourseEnrollment;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.IValidationState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationFailedState;
import dal.csci5308.project.group15.elearning.models.student.courseEnrollmentValidate.state.ValidationSuccessState;
import dal.csci5308.project.group15.elearning.persistence.student.StudentCourseEnrollmentPersistenceSingleton;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public abstract class EnrollmentValidatorTemplate {

    private IValidationState validationState;

    public IValidationState validateStudentRegistration(IStudentCourseEnrollment studentCourseEnrollment) {
        if (validateAvailableSeats(studentCourseEnrollment)) {
            if (validateStudentAlreadyRegistered(studentCourseEnrollment)) {
                validationState = validateStudentEligibility(studentCourseEnrollment, validationState);
                if (validationState.isValid()) {
                    return new ValidationSuccessState();
                }
            }
        }
        return validationState;
    }

    Boolean validateAvailableSeats(IStudentCourseEnrollment studentCourseEnrollment) {
        Integer enrolledSeats = studentCourseEnrollment.getEnrolledSeats();
        Integer totalSeats = studentCourseEnrollment.getTotalSeats();
        if (enrolledSeats < totalSeats) {
            return true;
        } else {
            studentCourseEnrollment.setRegistrationResult("Cannot Register! Seats are not available");
            validationState = new ValidationFailedState();
            validationState.setValidationResult("Cannot Register! Seats are not available");
            return false;
        }
    }

    Boolean validateStudentAlreadyRegistered(IStudentCourseEnrollment studentCourseEnrollment) {
        ArrayList<IStudentCourseEnrollment> enrolledCoursesByStudent;
        try {
            String courseIDToBeEnrolled = studentCourseEnrollment.getCourseInstanceID();
            enrolledCoursesByStudent = studentCourseEnrollment.loadByStudentNumber(StudentCourseEnrollmentPersistenceSingleton.GetMySqlStudentCourseEnrollmentPersistenceInstance(), studentCourseEnrollment.getStudentNumber());
            for (IStudentCourseEnrollment enrolledCourse : enrolledCoursesByStudent) {
                String enrolledCourseID = enrolledCourse.getCourseInstanceID();
                if (courseIDToBeEnrolled.equals(enrolledCourseID)) {
                    studentCourseEnrollment.setRegistrationResult("Cannot Register! Student is already Enrolled!");
                    validationState = new ValidationFailedState();
                    validationState.setValidationResult("Cannot Register! Student is already Enrolled!");
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    abstract IValidationState validateStudentEligibility(IStudentCourseEnrollment studentCourseEnrollment, IValidationState validationState);
}
