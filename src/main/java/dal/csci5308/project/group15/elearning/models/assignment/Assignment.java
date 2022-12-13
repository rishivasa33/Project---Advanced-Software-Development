package dal.csci5308.project.group15.elearning.models.assignment;

import dal.csci5308.project.group15.elearning.assignment.AssignmentParams;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.mysqlpersistence.MySqlCreateAssignmentPersistence;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Assignment {

    private String subId;
    private String assignmentId;
    private String assignmentTitle;
    private String assignmentDescription;
    private Date assignmentEndDate;
    private Date assignmentStartDate;
    private String filepath ;

    CreateAssignmentPersistence createAssignmentPersistence;


    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentDescription() {
        return assignmentDescription;
    }

    public void setAssignmentDescription(String assignmentDescription) {
        this.assignmentDescription = assignmentDescription;
    }

    public Date getAssignmentEndDate() {
        return assignmentEndDate;
    }

    public void setAssignmentEndDate(Date assignmentEndDate) {
        this.assignmentEndDate = assignmentEndDate;
    }

    public Date getAssignmentStartDate() {
        return assignmentStartDate;
    }

    public void setAssignmentStartDate(Date assignmentStartDate) {
        this.assignmentStartDate = assignmentStartDate;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Assignment(AssignmentParams assObj){
        subId =assObj.getSubId();
        assignmentId = assObj.getAssignmentId();
        assignmentTitle = assObj.getAssignmentTitle();
        assignmentDescription = assObj.getAssignmentDescription();
        assignmentStartDate = assObj.getAssignmentStartDate();
        assignmentEndDate = assObj.getAssignmentEndDate();
        filepath = assObj.getAssignmentFilePath();
        createAssignmentPersistence = CreateAssignmentPersistenceSingleton.GetCreateAssignmentPersistence();
    }

    public Assignment(){
        createAssignmentPersistence = CreateAssignmentPersistenceSingleton.GetCreateAssignmentPersistence();
    }

    public void Save() throws SQLException {
        createAssignmentPersistence.save(this);
    }

    public List<String> loadAssignmentList(String assignmentId) throws SQLException{
      List<String> assignmentList = createAssignmentPersistence.loadAssignmentList(assignmentId);
      return assignmentList;
    }

    public ArrayList<Assignment> LoadBySubjectId(String subId){
        return null;
    }


    public List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException {
        List<Assignment> assignmentList = createAssignmentPersistence.loadAssignmentDetails(assignmentId);
        return assignmentList;
    }
}
