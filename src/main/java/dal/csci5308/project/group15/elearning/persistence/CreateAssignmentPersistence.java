package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;

import java.sql.SQLException;

public interface CreateAssignmentPersistence {

    void save(Assignment assignment) throws SQLException;

    public Assignment load(String assignmentId);
}
