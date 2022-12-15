package dal.csci5308.project.group15.elearning.models.student;

public interface IStudentFactory {
    StudentCourseEnrollment createStudentCourseEnrollmentInstance(String courseInstanceID, String studentNumber, String courseTerm);

    StudentCourseEnrollment createStudentCourseEnrollmentInstanceForSave(String courseInstanceID, String studentNumber, String courseTerm, Integer enrolledSeats, Integer totalSeats);

    StudentCourseEnrollment createStudentCourseEnrollmentInstanceForLoad(String studentNumber);

    StudentDetails createStudentDetailsInstance(Integer studentUserID, String studentNumber, String studentProgram);

    StudentDetails createEmptyStudentDetailsInstance();
}
