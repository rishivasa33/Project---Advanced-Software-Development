package dal.csci5308.project.group15.elearning.models.student;

public class StudentCourseEnrollmentFactory implements IStudentCourseEnrollmentFactory {
    @Override
    public StudentCourseEnrollment createStudentCourseEnrollmentInstance(String courseInstanceID, String studentNumber, String courseTerm) {
        return new StudentCourseEnrollment(courseInstanceID, studentNumber, courseTerm);
    }

    @Override
    public StudentCourseEnrollment createStudentCourseEnrollmentInstanceForLoad(String studentNumber) {
        return new StudentCourseEnrollment(studentNumber);
    }
}
