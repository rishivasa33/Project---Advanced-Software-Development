package dal.csci5308.project.group15.elearning.models.course.courseContent;


import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentView;

public class CourseContentFactory {

    public TextCourseContent CreateTextCourseContent(String contentHeading, String contentText){
        return new TextCourseContent(contentHeading, contentText);
    }

    public TextCourseContent CreateTextCourseContent(int contentId, String contentHeading, String contentText){
        TextCourseContent textCourseContent = new TextCourseContent(contentHeading, contentText);
        textCourseContent.SetContentId(contentId);
        return textCourseContent;
    }

    public TextCourseContent CreateTextCourseContent(CourseContentView courseContentView){
        return new TextCourseContent(courseContentView.getCourseModuleContentHeading(), courseContentView.getCourseModuleContentText());
    }

    public CourseModule CreateCourseModule(String moduleName){
        return new CourseModule(moduleName);
    }

    public CourseModule CreateCourseModule(String moduleName, int courseModuleId){
        CourseModule courseModule = new CourseModule(moduleName);
        courseModule.SetModuleID(courseModuleId);
        return courseModule;
    }
}
