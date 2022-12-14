package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public interface CreateAssignmentPersistence {

    void save(Assignment assignment) throws SQLException;

    Assignment load(String assignmentId);

    List<String> loadAssignmentList(String courseInstanceId) throws SQLException, FileNotFoundException;

    void saveStudentAssignment(Assignment assignment, String studentNumber, String assignmentId) throws SQLException;

    List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException;
}
