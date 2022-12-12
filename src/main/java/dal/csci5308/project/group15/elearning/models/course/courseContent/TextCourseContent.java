package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.persistence.CourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.CourseContentPersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.TextCourseContentPersistence;

import java.sql.SQLException;

public class TextCourseContent extends CourseContent{
    private String textContent;
    private TextCourseContentPersistence courseContentPersistence;

    TextCourseContent(String heading, String textContent){
        super(heading);
        this.textContent = textContent;
        courseContentPersistence = CourseContentPersistenceSingleton.GetTextCourseContentPersistence();
    }

    public String GetTextContent(){
        return textContent;
    }

    public void Save(int courseModuleId) throws SQLException {
        int id = courseContentPersistence.Save(this,courseModuleId);
        SetContentId(id);
    }

    public CourseContent Load(int courseContentID) throws SQLException {
        CourseContent courseContent =  courseContentPersistence.Load(courseContentID);
        courseContent.SetContentId(courseContentID);
        return courseContent;
    }

    public boolean IsTextContent(){
        return true;
    }


}
