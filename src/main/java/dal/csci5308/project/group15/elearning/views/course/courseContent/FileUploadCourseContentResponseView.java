package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;

import javax.json.*;

public class FileUploadCourseContentResponseView extends CourseContentResponseView{

    private String courseId;
    private int courseModuleId;

    private Integer courseContentId;
    private String courseModuleContentHeading;
    private String courseModuleContentFilePath;

    public JsonObject GetJsonValue(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", true)
                .add("courseId", courseId)
                .add("courseModuleId", courseModuleId)
                .add("courseModuleContentId", courseContentId)
                .add("courseModuleContentHeading", courseModuleContentHeading)
                .add("courseModuleContentType", "File")
                .add("courseModuleContentFilePath", courseModuleContentFilePath).build();
        return value;
    }

    FileUploadCourseContentResponseView (String courseId, int courseModuleId, FileCourseContent pdfFileCourseContent){
        this.courseId = courseId;
        this.courseModuleId = courseModuleId;
        this.courseContentId = pdfFileCourseContent.GetContentId();
        this.courseModuleContentHeading = pdfFileCourseContent.GetContentHeading();
        this.courseModuleContentFilePath = pdfFileCourseContent.GetFilePath();
    }

    FileUploadCourseContentResponseView(String courseId, int courseModuleId){

        this.courseId = courseId;
        this.courseModuleId = courseModuleId;

    }



    public String getSerializedStringForSuccess(){
        return GetJsonValue().toString();
    }


}
