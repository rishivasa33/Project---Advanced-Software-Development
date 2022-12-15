package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;

import javax.json.Json;
import javax.json.JsonObject;


public class FileUploadCourseContentResponseView extends CourseContentResponseView{

    private String courseId;
    private int courseModuleId;

    private Integer courseContentId;
    private String courseModuleContentHeading;
    private String courseModuleContentFilePath;

    private String courseModuleContentFileType;

    public JsonObject GetJsonValue(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", true)
                .add("courseId", courseId)
                .add("courseModuleId", courseModuleId)
                .add("courseModuleContentId", courseContentId)
                .add("courseModuleContentHeading", courseModuleContentHeading)
                .add("courseModuleContentType", "File")
                .add("courseModuleContentFileType",  this.courseModuleContentFileType)
                .add("courseModuleContentFilePath", courseModuleContentFilePath).build();
        return value;
    }

    FileUploadCourseContentResponseView (String courseId, int courseModuleId, FileCourseContent fileCourseContent){
        this.courseId = courseId;
        this.courseModuleId = courseModuleId;
        this.courseContentId = fileCourseContent.GetContentId();
        this.courseModuleContentHeading = fileCourseContent.GetContentHeading();
        this.courseModuleContentFilePath = fileCourseContent.GetFilePath();
        this.courseModuleContentFileType = fileCourseContent.GetState().GetFileType();
    }

    FileUploadCourseContentResponseView(String courseId, int courseModuleId){

        this.courseId = courseId;
        this.courseModuleId = courseModuleId;

    }



    public String getSerializedStringForSuccess(){
        return GetJsonValue().toString();
    }


}
