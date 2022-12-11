package dal.csci5308.project.group15.elearning.persistence.mysqlpersistence;

import dal.csci5308.project.group15.elearning.database.Database;
import dal.csci5308.project.group15.elearning.models.assignment.Assignment;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class MySqlCreateAssignmentPersistence implements CreateAssignmentPersistence {

   public void save(Assignment assignment) throws SQLException {
       Connection connection = Database.instance().getConnection();
       CallableStatement stmt = connection.prepareCall("call put_assignment_details(?,?,?,?,?,?,?)");
       stmt.setString(1, assignment.getSubId());
       stmt.setString(2,assignment.getAssignmentId());
       stmt.setString(3,assignment.getAssignmentTitle());
       stmt.setString(4,assignment.getAssignmentDescription());
       stmt.setDate(5,assignment.getAssignmentStartDate());
       stmt.setDate(6,assignment.getAssignmentEndDate());
       stmt.setString(7,assignment.getFilepath());

       int result = stmt.executeUpdate();
       System.out.println(result);
       connection.commit();
       connection.close();
    }

    public Assignment load(String assignmentId){
        return null;
    }
}
