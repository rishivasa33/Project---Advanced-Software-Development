package dal.csci5308.project.group15.elearning.models.student;

public class StudentFactory implements IStudentFactory {
    @Override
    public StudentCourseEnrollment createStudentCourseEnrollmentInstance(String courseInstanceID, String studentNumber, String courseTerm) {
        return new StudentCourseEnrollment(courseInstanceID, studentNumber, courseTerm);
    }

    @Override
    public StudentCourseEnrollment createStudentCourseEnrollmentInstanceForLoad(String studentNumber) {
        return new StudentCourseEnrollment(studentNumber);
    }

    @Override
    public StudentDetails createStudentDetailsInstance(Integer studentUserID, String studentNumber, String studentProgram){
        return new StudentDetails(studentUserID, studentNumber, studentProgram);
    }

    @Override
    public StudentDetails createEmptyStudentDetailsInstance(){
        return new StudentDetails();
    }
}
