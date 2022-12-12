package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

import javax.json.*;
import java.util.ArrayList;

public class JsonCourseContentView implements CourseContentView{
    private String courseId;
    private int courseModuleId;

    private Integer courseContentId;
    private String courseModuleContentHeading;
    private String courseModuleContentText;

    private JsonObject GetJsonValue(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", true)
                .add("courseId", getCourseId())
                .add("courseModuleId", getCourseModuleId())
                .add("courseModuleContentId", getCourseModuleContentId())
                .add("courseModuleContentHeading", getCourseModuleContentHeading())
                .add("courseModuleContentText", getCourseModuleContentText()).build();
        return value;
    }

    JsonCourseContentView(){

    }


    JsonCourseContentView(String requestBody){
        JsonObject jsonObject = CreateJsonObjectFromRequestBody.GetJsonObjectFromRequestBody(requestBody);
        this.courseId = jsonObject.getString("courseId");
        String courseModuleIdString = jsonObject.getString("courseModuleId");
        this.courseModuleContentHeading = jsonObject.getString("courseContentHeading");
        this.courseModuleId = Integer.parseInt(courseModuleIdString);
        this.courseModuleContentText = jsonObject.getString("courseContentText");
        this.courseContentId = null;

    }

    public void Update(TextCourseContent textCourseContent){
        this.courseContentId = textCourseContent.GetContentId();
        this.courseModuleContentHeading = textCourseContent.GetContentHeading();
        this.courseModuleContentText = textCourseContent.GetTextContent();
    }

    public String getCourseId(){
        return courseId;
    }

    public int getCourseModuleContentId(){
        return courseContentId;
    }
    public int getCourseModuleId(){
        return courseModuleId;
    }

    public String getCourseModuleContentHeading(){
        return courseModuleContentHeading;
    }

    public String getCourseModuleContentText(){
        return courseModuleContentText;
    }

    public String getSerializedStringForSuccess(){

       return GetJsonValue().toString();

    }

    public String getSerializedStringForSuccess(String courseId, int courseModuleId, ArrayList<CourseContent> courseContentArrayList){

        this.courseId = courseId;
        this.courseModuleId = courseModuleId;

        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("success", true);


        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for(CourseContent courseContent : courseContentArrayList){
            this.courseContentId = courseContent.GetContentId();
            this.courseModuleContentHeading = courseContent.GetContentHeading();
            if(courseContent.IsTextContent()){
                TextCourseContent textCourseContent = (TextCourseContent) courseContent;
                this.courseModuleContentText = textCourseContent.GetTextContent();
                jsonArrayBuilder.add(GetJsonValue());
            }

        }
        JsonArray jsonArray =  jsonArrayBuilder.build();
        jsonObjectBuilder.add("contentList", jsonArray);
        JsonObject jsonObject = jsonObjectBuilder.build();


        return jsonObject.toString();
    }

    public String getSerializedStringForFailure(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", false).build();
        return value.toString();
    }
}
