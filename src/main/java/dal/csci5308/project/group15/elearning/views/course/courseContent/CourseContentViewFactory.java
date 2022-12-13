package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import org.springframework.web.multipart.MultipartFile;

import javax.json.JsonObject;
import java.io.IOException;

public class CourseContentViewFactory {

    public CourseContentRequestView CreateJsonCourseContentView(String requestBody){
        return new TextCourseContentRequestView(requestBody);
    }



    public TextCourseContentResponseView CreateTextCourseContentResponseView(String courseId, int courseModuleId, TextCourseContent textCourseContent){
        return new TextCourseContentResponseView(courseId, courseModuleId, textCourseContent);
    }

    public TextCourseContentResponseView CreateTextCourseContentResponseView(String courseId, int courseModuleId){
        return new TextCourseContentResponseView(courseId, courseModuleId);
    }

    public FileUploadCourseContentResponseView CreatePdfCourseContentResponseView(String courseId, int courseModuleId, FileCourseContent pdfFileCourseContent){
        return new FileUploadCourseContentResponseView(courseId, courseModuleId, pdfFileCourseContent);
    }

    public FileUploadCourseContentResponseView CreatePdfCourseContentResponseView(String courseId, int courseModuleId){
        return new FileUploadCourseContentResponseView(courseId, courseModuleId);
    }

    public CourseContentRequestView CreateJsonCourseContentView(){
        return new TextCourseContentRequestView();
    }

    public CourseContentRequestView CreateFormDataCourseContentView(String courseId, String courseModuleId, String courseModuleContentHeading, MultipartFile courseContentFile) throws IOException {
        return new FileUploadCourseContentRequestView(courseId, courseModuleId, courseModuleContentHeading, courseContentFile);
    }

    public FetchModuleContentFileRequestView CreateFetchModuleContentFileRequestView(JsonObject jsonObject){
        return new FetchModuleContentFileRequestView(jsonObject);
    }

    public FetchFileContentResponseView CreateFetchFileContentResponseView(FileCourseContent fileCourseContent){
        return new FetchFileContentResponseView(fileCourseContent);
    }


}
