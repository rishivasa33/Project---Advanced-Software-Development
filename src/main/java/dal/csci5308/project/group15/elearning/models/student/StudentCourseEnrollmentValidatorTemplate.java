package dal.csci5308.project.group15.elearning.models.student;

public abstract class StudentCourseEnrollmentValidatorTemplate {
    String studentNumber;
    String courseInstanceID;
    String courseTerm;

    StudentCourseEnrollmentValidatorTemplate(){}

    public void validateAvailableSeats(Integer enrolledSeats, Integer totalSeats){

    }
}
