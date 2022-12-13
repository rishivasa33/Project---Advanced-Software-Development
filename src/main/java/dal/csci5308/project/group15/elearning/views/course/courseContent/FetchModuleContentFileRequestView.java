package dal.csci5308.project.group15.elearning.views.course.courseContent;

import javax.json.JsonObject;

public class FetchModuleContentFileRequestView {


    private String courseId;
    private int moduleId;
    private int moduleContentId;

    FetchModuleContentFileRequestView(JsonObject jsonObject){
        this.courseId = jsonObject.getString("courseId");
        this.moduleId = Integer.parseInt(jsonObject.getString("courseModuleId"));
        this.moduleContentId = Integer.parseInt(jsonObject.getString("courseModuleContentId"));
    }

    public String getCourseId() {
        return courseId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public int getModuleContentId() {
        return moduleContentId;
    }
}
