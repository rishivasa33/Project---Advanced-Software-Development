package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.student;

import dal.csci5308.project.group15.elearning.models.student.IStudentDetails;
import dal.csci5308.project.group15.elearning.models.student.StudentDetails;
import dal.csci5308.project.group15.elearning.persistence.student.IStudentDetailsPersistence;

import java.util.ArrayList;

public class MockStudentDetails implements IStudentDetailsPersistence {
    @Override
    public void save(StudentDetails studentDetails) {

    }

    @Override
    public IStudentDetails loadByStudentID(Integer userID) {
        return null;
    }

    @Override
    public IStudentDetails loadByStudentName(String userName) {
        return null;
    }
}
