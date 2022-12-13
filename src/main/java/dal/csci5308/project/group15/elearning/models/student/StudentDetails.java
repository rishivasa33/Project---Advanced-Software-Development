package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;

public class StudentDetails implements IStudentDetails{
    private Integer studentUserID;
    private String studentNumber;
    private String studentProgram;

    public StudentDetails(Integer studentUserID, String studentNumber, String studentProgram) {
        this.studentUserID = studentUserID;
        this.studentNumber = studentNumber;
        this.studentProgram = studentProgram;
    }

    public StudentDetails() {
    }

    @Override
    public Integer getStudentUserID() {
        return studentUserID;
    }

    @Override
    public String getStudentNumber() {
        return studentNumber;
    }

    @Override
    public String getStudentProgram() {
        return studentProgram;
    }

    @Override
    public void save(IStudentDetailsPersistence iStudentDetailsPersistence){
        iStudentDetailsPersistence.save(this);
    }

    @Override
    public IStudentDetails loadByUserID(IStudentDetailsPersistence iStudentDetailsPersistence, Integer userID)  {
        return iStudentDetailsPersistence.loadByStudentID(userID);
    }

    @Override
    public IStudentDetails loadByUserName(IStudentDetailsPersistence iStudentDetailsPersistence, String userName) {
        return iStudentDetailsPersistence.loadByStudentName(userName);
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                "studentUserID=" + studentUserID +
                ", studentNumber='" + studentNumber + '\'' +
                ", studentProgram='" + studentProgram + '\'' +
                '}';
    }
}
