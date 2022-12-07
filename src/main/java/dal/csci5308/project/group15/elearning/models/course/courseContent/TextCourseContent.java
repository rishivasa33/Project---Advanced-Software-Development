package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.persistence.CourseContentPersistence;
import dal.csci5308.project.group15.elearning.persistence.TextContentPersistenceSingleton;

public class TextCourseContent extends CourseContent{
    private String textContent;
    private CourseContentPersistence courseContentPersistence;

    TextCourseContent(String heading, String textContent){
        super(heading);
        this.textContent = textContent;
        courseContentPersistence = TextContentPersistenceSingleton.GetTextCourseContentPersistence();
    }

    public String GetTextContent(){
        return textContent;
    }

    public void Save(){
        int id = courseContentPersistence.Save(this);
        SetContentId(id);
    }

    public CourseContent Load(int courseContentID){
        CourseContent courseContent =  courseContentPersistence.Load(courseContentID);
        courseContent.SetContentId(courseContentID);
        return courseContent;
    }


}
