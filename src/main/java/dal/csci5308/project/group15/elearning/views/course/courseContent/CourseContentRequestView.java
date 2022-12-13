package dal.csci5308.project.group15.elearning.views.course.courseContent;

public interface CourseContentRequestView {

    public String getCourseId();
    public int getCourseModuleId();

    public String getCourseModuleContentHeading();

    public String getCourseModuleContentText();

    public String getFilePath();

}
