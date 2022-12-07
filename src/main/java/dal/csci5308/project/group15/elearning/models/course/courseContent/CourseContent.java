package dal.csci5308.project.group15.elearning.models.course.courseContent;


// command pattern to do stuff with various content types.

public abstract class CourseContent {

    private String contentHeading;
    private int contentId;

    CourseContent(String contentHeading){
        this.contentHeading = contentHeading;
    }

    public String GetContentHeading(){
        return contentHeading;
    }

    public int GetContentId(){
        return contentId;
    }

    protected void SetContentId(int contentId){
        this.contentId = contentId;
    }

    public abstract void Save();

    public abstract CourseContent Load(int courseContentID);

}
