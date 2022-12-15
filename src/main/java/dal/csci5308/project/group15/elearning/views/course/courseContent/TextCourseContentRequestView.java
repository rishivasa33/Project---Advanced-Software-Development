package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

import javax.json.JsonObject;


public class TextCourseContentRequestView implements CourseContentRequestView {
    private String courseId;
    private int courseModuleId;

    private Integer courseContentId;
    private String courseModuleContentHeading;
    private String courseModuleContentText;



    TextCourseContentRequestView(){

    }


    TextCourseContentRequestView(String requestBody){
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


    public String getFilePath(){
        return "";
    }


}
