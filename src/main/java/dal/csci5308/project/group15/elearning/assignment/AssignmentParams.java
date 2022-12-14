package dal.csci5308.project.group15.elearning.assignment;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

public class AssignmentParams {

    private String subId;
    private String assignmentId;
    private String assignmentTitle;
    private String assignmentDescription;
    private Date assignmentEndDate;
    private Date assignmentStartDate;
    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public String getAssignmentFilePath() {
        String pathvalue = null;
        try {
            Path path = Paths.get("AssignmentProffAttachments/" + file.getOriginalFilename());
            pathvalue = path.toString();
            Files.copy(getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathvalue;
    }

    public String getStudentAssignmentFilePath() {
        String pathvalue = null;
        try {
            Path path = Paths.get("StudentAssignmentAttachments/" + file.getOriginalFilename());
            pathvalue = path.toString();
            Files.copy(getFile().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathvalue;
    }


}
