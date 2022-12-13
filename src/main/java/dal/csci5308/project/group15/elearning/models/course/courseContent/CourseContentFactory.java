package dal.csci5308.project.group15.elearning.models.course.courseContent;


import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentRequestView;

public class CourseContentFactory {

    public TextCourseContent CreateTextCourseContent(String contentHeading, String contentText){
        return new TextCourseContent(contentHeading, contentText);
    }

    public TextCourseContent CreateTextCourseContent(int contentId, String contentHeading, String contentText){
        TextCourseContent textCourseContent = new TextCourseContent(contentHeading, contentText);
        textCourseContent.SetContentId(contentId);
        return textCourseContent;
    }

    public TextCourseContent CreateTextCourseContent(CourseContentRequestView courseContentView){
        return new TextCourseContent(courseContentView.getCourseModuleContentHeading(), courseContentView.getCourseModuleContentText());
    }

    public FileCourseContent CreatePdfCourseContent(CourseContentRequestView courseContentView){
        return new FileCourseContent(courseContentView.getCourseModuleContentHeading(), courseContentView.getFilePath());
    }

    public FileCourseContent CreatePdfCourseContent(String courseModuleContentName, String courseModuleContentFilePath){
        return new FileCourseContent(courseModuleContentName,courseModuleContentFilePath);
    }

    public FileCourseContent CreatePdfCourseContent(int courseContentId, String courseModuleContentName, String courseModuleContentFilePath){
        FileCourseContent pdfFileCourseContent = new FileCourseContent(courseModuleContentName,courseModuleContentFilePath);
        pdfFileCourseContent.SetContentId(courseContentId);
        return pdfFileCourseContent;
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
