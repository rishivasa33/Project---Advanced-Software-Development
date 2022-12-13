package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.assignment;

import dal.csci5308.project.group15.elearning.assignment.AssignmentParams;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MockDBCreateAssignmentPersistence implements CreateAssignmentPersistence {

    public void save(Assignment assignment){

    }

    public Assignment load(String assignmentId){

        AssignmentParams assignmentParams = new AssignmentParams();
        assignmentParams.setAssignmentId("F22CSCI5308_A1");
        assignmentParams.setSubId("F22CSCI5308");
        assignmentParams.setAssignmentTitle("Assignment 1");
        assignmentParams.setAssignmentDescription("Assignment 1 Content");
        assignmentParams.setAssignmentStartDate(new Date(6556));
        assignmentParams.setAssignmentEndDate(new Date(6557));
        Assignment assignment = new Assignment(assignmentParams);
        return assignment;
    }

    @Override
    public List<String> loadAssignmentList(String courseInstanceId) throws SQLException {
        return null;
    }

    @Override
    public List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException {
        return null;
    }

}
