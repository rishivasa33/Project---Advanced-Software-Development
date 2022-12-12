package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.assignment.AssignmentParams;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateAssignmentTest {
    @Test
    void TestAssignmentSave() throws SQLException {

        AssignmentParams assignmentParams = new AssignmentParams();
        assignmentParams.setAssignmentId("F22CSCI5308_A1");
        assignmentParams.setSubId("F22CSCI5308");
        assignmentParams.setAssignmentTitle("Assignment 1");
        assignmentParams.setAssignmentDescription("Assignment 1 Content");
        assignmentParams.setAssignmentStartDate(new Date(6556));
        assignmentParams.setAssignmentEndDate(new Date(6557));
        Assignment assignment = new Assignment(assignmentParams);
       // Assertions.assertDoesNotThrow(assignment.Save());

        assignment.Save();
        assertEquals(assignment.getAssignmentId(), "F22CSCI5308_A1");
        assertEquals(assignment.getSubId(), "F22CSCI5308");
        assertEquals(assignment.getAssignmentTitle(), "Assignment 1");
        assertEquals(assignment.getAssignmentDescription(), "Assignment 1 Content");
        assertEquals(assignment.getAssignmentStartDate(), new Date(6556));
        assertEquals(assignment.getAssignmentEndDate(), new Date(6557));
    }

    void TestAssignmentLoad() throws SQLException {


        Assignment assignment = new Assignment();

       assignment = assignment.Load("F22CSCI5308_A1");
        assertEquals(assignment.getAssignmentId(), "F22CSCI5308_A1");
        assertEquals(assignment.getSubId(), "F22CSCI5308");
        assertEquals(assignment.getAssignmentTitle(), "Assignment 1");
        assertEquals(assignment.getAssignmentDescription(), "Assignment 1 Content");
        assertEquals(assignment.getAssignmentStartDate(), new Date(6556));
        assertEquals(assignment.getAssignmentEndDate(), new Date(6557));

    }
}
