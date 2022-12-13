package dal.csci5308.project.group15.elearning.models.student;

import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;

import java.util.ArrayList;

public interface IStudentDetails {
    Integer getStudentUserID();

    String getStudentNumber();

    String getStudentProgram();

    void save(IStudentDetailsPersistence iStudentDetailsPersistence);

    IStudentDetails loadByUserID(IStudentDetailsPersistence iStudentDetailsPersistence, Integer userID);
    IStudentDetails loadByUserName(IStudentDetailsPersistence iStudentDetailsPersistence, String userName);
}
