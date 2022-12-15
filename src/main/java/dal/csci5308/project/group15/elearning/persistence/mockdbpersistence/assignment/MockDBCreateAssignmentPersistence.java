package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.assignment;

import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.models.assignment.AssignmentParams;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;
import org.springframework.mock.web.MockMultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MockDBCreateAssignmentPersistence implements CreateAssignmentPersistence {

    public void save(Assignment assignment) {
    }

    @Override
    public Assignment load(String assignmentId) {
        return null;
    }


    @Override
    public List<String> loadAssignmentList(String courseInstanceId) throws FileNotFoundException {
        AssignmentParams assignmentParams = new AssignmentParams();
        assignmentParams.setAssignmentId("F22CSCI5308_A1");
        assignmentParams.setSubId("F22CSCI5308");
        assignmentParams.setAssignmentTitle("Assignment 1");
        assignmentParams.setAssignmentDescription("Assignment 1 Content");
        assignmentParams.setAssignmentStartDate(new Date(1969 - 12 - 31));
        assignmentParams.setAssignmentEndDate(new Date(1969 - 12 - 31));
        String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\dal\\csci5308\\project\\group15\\elearning\\models\\course\\CourseTests.java";
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] filebyte = new byte[5];
        filebyte[0] = '1';
        assignmentParams.setFile(new MockMultipartFile(String.valueOf(file), file.getName(), "text/plain", filebyte));

        List<String> assignmentList = new ArrayList();
        Assignment assignment = new Assignment(assignmentParams);
        assignmentList.add(assignment.getAssignmentTitle());
        assignmentList.add(assignment.getAssignmentId());
        assignmentList.add(assignment.getSubId());
        assignmentList.add(assignment.getAssignmentDescription());
        assignmentList.add(assignment.getAssignmentStartDate().toString());
        assignmentList.add(assignment.getAssignmentEndDate().toString());
        return assignmentList;
    }


    @Override
    public void saveStudentAssignment(Assignment assignment, String studentNumber, String assignmentId) {

    }

    @Override
    public List<Assignment> loadAssignmentDetails(String assignmentId) {

        Assignment assignment_model = new Assignment();
        assignment_model.setSubId("F22CSCI5308");
        assignment_model.setAssignmentId("CSCI5308A1");
        assignment_model.setAssignmentTitle("ASSIGNMENT1");
        assignment_model.setAssignmentDescription("Assignment 1");
        List<Assignment> assignment = new ArrayList();
        assignment.add(assignment_model);
        return assignment;
    }

}
