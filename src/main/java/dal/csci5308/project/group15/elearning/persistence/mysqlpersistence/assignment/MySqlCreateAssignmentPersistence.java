package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.assignment;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySqlCreateAssignmentPersistence implements CreateAssignmentPersistence {

    public void save(Assignment assignment) throws SQLException {
        Connection connection = Database.instance().getConnection();
        CallableStatement stmt = connection.prepareCall("call put_assignment_details(?,?,?,?,?,?,?)");
        stmt.setString(1, assignment.getSubId());
        stmt.setString(2, assignment.getAssignmentId());
        stmt.setString(3, assignment.getAssignmentTitle());
        stmt.setString(4, assignment.getAssignmentDescription());
        stmt.setDate(5, assignment.getAssignmentStartDate());
        stmt.setDate(6, assignment.getAssignmentEndDate());
        stmt.setString(7, assignment.getFilepath());

        int result = stmt.executeUpdate();
        connection.commit();
        connection.close();
    }

    @Override
    public Assignment load(String assignmentId) {
        return null;
    }

    public List<String> loadAssignmentList(String courseInstanceId) throws SQLException {
        Connection connection = Database.instance().getConnection();
        PreparedStatement stmt = connection.prepareStatement("call getAssignmentList(?)");
        stmt.setString(1, courseInstanceId);
        ResultSet rs = stmt.executeQuery();
        List<String> assignmentList = new ArrayList<>();
        while (rs.next()) {
            assignmentList.add(rs.getString(1));
        }
        connection.commit();
        connection.close();
        return assignmentList;
    }

    @Override
    public void saveStudentAssignment(Assignment assignment, String studentNumber, String assignmentId) throws SQLException {

        Connection connection = Database.instance().getConnection();
        CallableStatement stmt = connection.prepareCall("call put_assignment_submission_details(?,?,?)");
        stmt.setString(1, studentNumber);
        stmt.setString(2, assignmentId);
        stmt.setString(3, assignment.getStudentFilePath());
        int result = stmt.executeUpdate();
        connection.commit();
        connection.close();

    }

    public List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException {
        Connection connection = Database.instance().getConnection();
        PreparedStatement stmt = connection.prepareStatement("call getAssignmentDetails(?)");
        stmt.setString(1, assignmentId);
        ResultSet rs = stmt.executeQuery();
        List<Assignment> assignmentList = new ArrayList<>();

        while (rs.next()) {
            Assignment assignment_model = new Assignment();
            assignment_model.setSubId(rs.getString(1));
            assignment_model.setAssignmentId(rs.getString(2));
            assignment_model.setAssignmentTitle(rs.getString(3));
            assignment_model.setAssignmentDescription(rs.getString(4));
            assignment_model.setAssignmentStartDate(rs.getDate(5));
            assignment_model.setAssignmentEndDate(rs.getDate(6));
            assignment_model.setFilepath(rs.getString(7));
            assignmentList.add(assignment_model);
        }
        connection.commit();
        connection.close();
        return assignmentList;
    }
}
