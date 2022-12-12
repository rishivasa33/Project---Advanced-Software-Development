package dal.csci5308.project.group15.elearning.models.student;

public interface IStudentCourseEnrollmentFactory {
    StudentCourseEnrollment createStudentCourseEnrollmentInstance(String courseInstanceID, String studentNumber, String courseTerm);

    StudentCourseEnrollment createStudentCourseEnrollmentInstanceForLoad(String studentNumber);
}
