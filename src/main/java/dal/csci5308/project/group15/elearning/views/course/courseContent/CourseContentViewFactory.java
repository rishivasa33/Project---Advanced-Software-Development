package dal.csci5308.project.group15.elearning.views.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.TextCourseContent;

public class CourseContentViewFactory {

    public CourseContentView CreateJsonCourseContentView(String requestBody){
        return new JsonCourseContentView(requestBody);
    }

    public CourseContentView CreateJsonCourseContentView(){
        return new JsonCourseContentView();
    }


}
