package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class CourseContentViewFactory {

    public CourseContentView CreateJsonCourseContentView(String requestBody){
        return new JsonCourseContentView(requestBody);
    }

    public CourseContentView CreateJsonCourseContentView(){
        return new JsonCourseContentView();
    }

    public CourseContentView CreateFormDataCourseContentView(String courseId, String courseModuleId, String courseModuleContentHeading, MultipartFile courseContentFile) throws IOException {
        return new FormDataCourseContentView(courseId, courseModuleId, courseModuleContentHeading, courseContentFile);
    }


}
