package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

import javax.json.*;
import java.util.ArrayList;

public class TextCourseContentResponseView extends CourseContentResponseView{
    private String courseId;
    private int courseModuleId;

    private Integer courseContentId;
    private String courseModuleContentHeading;
    private String courseModuleContentText;

    public JsonObject GetJsonValue(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", true)
                .add("courseId", courseId)
                .add("courseModuleId", courseModuleId)
                .add("courseModuleContentId", courseContentId)
                .add("courseModuleContentHeading", courseModuleContentHeading)
                .add("courseModuleContentType", "Text")
                .add("courseModuleContentText", courseModuleContentText).build();
        return value;
    }

    TextCourseContentResponseView(String courseId, int courseModuleId, TextCourseContent textCourseContent){

        this.courseId = courseId;
        this.courseContentId = textCourseContent.GetContentId();
        this.courseModuleContentText = textCourseContent.GetTextContent();
        this.courseModuleContentHeading = textCourseContent.GetContentHeading();
        this.courseModuleId = courseModuleId;

    }

    TextCourseContentResponseView(String courseId, int courseModuleId){

        this.courseId = courseId;
        this.courseModuleId = courseModuleId;

    }


    public String getSerializedStringForSuccess(){
        return GetJsonValue().toString();
    }


}
