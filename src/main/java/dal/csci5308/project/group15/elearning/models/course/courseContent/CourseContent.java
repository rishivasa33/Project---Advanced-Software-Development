package dal.csci5308.project.group15.elearning.models.course.courseContent;

import java.sql.SQLException;

public abstract class CourseContent {

    private String contentHeading;
    private Integer contentId;

    CourseContent(String contentHeading){
        this.contentHeading = contentHeading;
        contentId = null;
    }

    public String GetContentHeading(){
        return contentHeading;
    }

    public Integer GetContentId(){
        return contentId;
    }

    void SetContentId(int contentId){
        this.contentId = contentId;
    }

    public abstract void Save(int courseModuleId) throws SQLException;

    public abstract CourseContent Load(int courseContentID) throws SQLException;

    public boolean IsTextContent(){
        return false;
    }

}
