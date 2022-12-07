package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;

import java.util.ArrayList;

public class CourseModule {
    private String moduleName;
    private ArrayList<CourseContent> contentList;

    CourseModule(String moduleName){
        this.moduleName = moduleName;
    }

    void AddCourseContent(CourseContent courseContent){
        contentList.add(courseContent);
    }

    void RemoveCourseContent(Integer contentId){

    }

    public ArrayList<CourseContent> GetCourseContents(){
        return new ArrayList<>(contentList);
    }
}
