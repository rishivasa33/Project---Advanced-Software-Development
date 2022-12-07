package dal.csci5308.project.group15.elearning.models.course.courseContent;


public class CourseContentFactory {

    public TextCourseContent CreateTextCourseContent(String contentHeading, String contentText){
        return new TextCourseContent(contentHeading, contentText);
    }
}
