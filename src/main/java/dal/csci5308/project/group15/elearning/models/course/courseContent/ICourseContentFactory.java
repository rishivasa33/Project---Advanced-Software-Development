package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.views.course.courseContent.CourseContentRequestView;

public interface ICourseContentFactory {

    TextCourseContent CreateTextCourseContent(String contentHeading, String contentText);

    TextCourseContent CreateTextCourseContent(int contentId, String contentHeading, String contentText);

    TextCourseContent CreateTextCourseContent(CourseContentRequestView courseContentView);

    FileCourseContent CreateFileCourseContent(CourseContentRequestView courseContentView);

    public FileCourseContent CreateFileCourseContent(int courseContentId, String courseModuleContentName, String courseModuleContentFilePath);

    FileCourseContent CreateFileCourseContent(String courseModuleContentName, String courseModuleContentFilePath);

    CourseModule CreateCourseModule(String moduleName);

    CourseModule CreateCourseModule(String moduleName, int courseModuleId);

}
