package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import dal.csci5308.project.group15.elearning.views.ViewFactoriesCollection;

import javax.json.*;
import java.util.ArrayList;

public abstract class CourseContentResponseView {

    public static String getSerializedStringForFailure(){
        JsonObject value = Json.createObjectBuilder()
                .add("success", false).build();
        return value.toString();
    }

    public abstract String getSerializedStringForSuccess();

    public String getSerializedStringForSuccess(String courseId, int courseModuleId, ArrayList<CourseContent> courseContentArrayList){
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder().add("success", true);


        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();



        CourseContentViewFactory courseContentViewFactory = ViewFactoriesCollection.GetCourseContentViewFactory();

        for(CourseContent courseContent : courseContentArrayList){
            if(courseContent.IsTextContent()){
                TextCourseContentResponseView textCourseContentResponseView  = courseContentViewFactory.CreateTextCourseContentResponseView(courseId, courseModuleId, (TextCourseContent) courseContent);
                jsonArrayBuilder.add(textCourseContentResponseView.GetJsonValue());
            }
            else{
                FileUploadCourseContentResponseView fileUploadCourseContentResponseView = courseContentViewFactory.CreateFileCourseContentResponseView(courseId, courseModuleId, (FileCourseContent) courseContent);
                jsonArrayBuilder.add(fileUploadCourseContentResponseView.GetJsonValue());
            }
        }

        JsonArray jsonArray =  jsonArrayBuilder.build();
        jsonObjectBuilder.add("contentList", jsonArray);
        JsonObject jsonObject = jsonObjectBuilder.build();


        return jsonObject.toString();
    }
}
