package dal.csci5308.project.group15.elearning.models.assignment;

import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistence;
import dal.csci5308.project.group15.elearning.persistence.CreateAssignmentPersistenceSingleton;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Assignment {

    CreateAssignmentPersistence createAssignmentPersistence;
    private String subId;
    private String assignmentId;
    private String assignmentTitle;
    private String assignmentDescription;
    private Date assignmentEndDate;
    private Date assignmentStartDate;
    private String filepath;
    private String studentFilePath;

    public Assignment(AssignmentParams assObj) {
        subId = assObj.getSubId();
        assignmentId = assObj.getAssignmentId();
        assignmentTitle = assObj.getAssignmentTitle();
        assignmentDescription = assObj.getAssignmentDescription();
        assignmentStartDate = assObj.getAssignmentStartDate();
        assignmentEndDate = assObj.getAssignmentEndDate();
        filepath = assObj.getAssignmentFilePath();
        studentFilePath = assObj.getStudentAssignmentFilePath();
        createAssignmentPersistence = CreateAssignmentPersistenceSingleton.GetCreateAssignmentPersistence();
    }

    public Assignment() {
        createAssignmentPersistence = CreateAssignmentPersistenceSingleton.GetCreateAssignmentPersistence();
    }

    public CreateAssignmentPersistence getCreateAssignmentPersistence() {
        return createAssignmentPersistence;
    }

    public void setCreateAssignmentPersistence(CreateAssignmentPersistence createAssignmentPersistence) {
        this.createAssignmentPersistence = createAssignmentPersistence;
    }

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

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public void setStudentFilePath(String studentFilePath) {
        this.studentFilePath = studentFilePath;
    }

    public void Save() throws SQLException {
        createAssignmentPersistence.save(this);
    }

    public List<String> loadAssignmentList(String assignmentId) throws SQLException, FileNotFoundException {
        List<String> assignmentList = createAssignmentPersistence.loadAssignmentList(assignmentId);
        return assignmentList;
    }

    public List<Assignment> loadAssignmentDetails(String assignmentId) throws SQLException {
        List<Assignment> assignmentList = createAssignmentPersistence.loadAssignmentDetails(assignmentId);
        return assignmentList;
    }

    public void SaveStudentAssignment(String assignment_id, String student_number) throws SQLException {
        createAssignmentPersistence.saveStudentAssignment(this, student_number, assignment_id);
    }
}
