package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

import java.util.ArrayList;

public interface CourseContentView {

    public String getCourseId();
    public int getCourseModuleId();

    public String getCourseModuleContentHeading();

    public String getCourseModuleContentText();

    public String getSerializedStringForSuccess();

    public String getSerializedStringForFailure();

    public void Update(TextCourseContent textCourseContent);

    public String getSerializedStringForSuccess(String courseId, int courseModuleId, ArrayList<CourseContent> courseContentArrayList);

}
