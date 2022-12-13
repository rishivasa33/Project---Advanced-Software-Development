package dal.csci5308.project.group15.elearning.persistence.student;

import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.StudentDetails;

import java.util.ArrayList;

public interface IStudentDetailsPersistence {
    void save(StudentDetails studentDetails);

    IStudentDetails loadByStudentID(Integer userID);

    IStudentDetails loadByStudentName(String userName);
}
