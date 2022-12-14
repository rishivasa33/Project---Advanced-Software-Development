package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;

import java.sql.SQLException;
import java.util.List;

public interface CreateAssignmentPersistence {

    void save(Assignment assignment) throws SQLException;

    public Assignment load(String assignmentId);

    public List<String> loadAssignmentList(String courseInstanceId) throws SQLException;

    void saveStudentAssignment(Assignment assignment, String studentNumber, String assignmentId) throws SQLException;

    List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException;


}
