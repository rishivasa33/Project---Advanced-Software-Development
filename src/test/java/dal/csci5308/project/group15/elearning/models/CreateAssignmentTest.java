package dal.csci5308.project.group15.elearning.models;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.assignment.MockDBCreateAssignmentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreateAssignmentTest {

    @Test
    void TestLoadAssignmentList() throws SQLException, FileNotFoundException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        MockDBCreateAssignmentPersistence mocktest = new MockDBCreateAssignmentPersistence();
        List<String> result = mocktest.loadAssignmentList("F22CSCI5308");
        assertEquals(result.get(0), "Assignment 1");
        assertEquals(result.get(1), "F22CSCI5308_A1");
        assertEquals(result.get(2), "F22CSCI5308");
        assertEquals(result.get(3), "Assignment 1 Content");
        assertEquals(result.get(4), "1969-12-31");
        assertEquals(result.get(5), "1969-12-31");
    }

    @Test
    void TestLoadAssignmentDetails() throws SQLException{
        MockDBCreateAssignmentPersistence mocktest = new MockDBCreateAssignmentPersistence();
        List<Assignment> result = mocktest.loadAssignmentDetails("F22CSCI5308");
        assertEquals(result.get(0).getSubId(), "F22CSCI5308");
        assertEquals(result.get(0).getAssignmentId(), "CSCI5308A1");
        assertEquals(result.get(0).getAssignmentTitle(), "ASSIGNMENT1");
        assertEquals(result.get(0).getAssignmentDescription(), "Assignment 1");

    }

    @Test
    void TestAssignmentSave() throws SQLException {

//        AssignmentParams assignmentParams = new AssignmentParams();
//        assignmentParams.setAssignmentId("F22CSCI5308_A1");
//        assignmentParams.setSubId("F22CSCI5308");
//        assignmentParams.setAssignmentTitle("Assignment 1");
//        assignmentParams.setAssignmentDescription("Assignment 1 Content");
//        assignmentParams.setAssignmentStartDate(new Date(6556));
//        assignmentParams.setAssignmentEndDate(new Date(6557));
//        Assignment assignment = new Assignment(assignmentParams);
//
//        assignment.Save();
//        assertEquals(assignment.getAssignmentId(), "F22CSCI5308_A1");
//        assertEquals(assignment.getSubId(), "F22CSCI5308");
//        assertEquals(assignment.getAssignmentTitle(), "Assignment 1");
//        assertEquals(assignment.getAssignmentDescription(), "Assignment 1 Content");

    }
}
