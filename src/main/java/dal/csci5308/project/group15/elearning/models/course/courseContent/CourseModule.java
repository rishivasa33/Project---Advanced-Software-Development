package dal.csci5308.project.group15.elearning.models.course.courseContent;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.persistence.CourseModulePersistence;
import dal.csci5308.project.group15.elearning.persistence.CourseModulePersistenceSingleton;

import java.util.ArrayList;

public class CourseModule {
    private String moduleName;

    private int moduleId;
    private ArrayList<CourseContent> contentList;

    private CourseModulePersistence courseModulePersistence;

    CourseModule(String moduleName){
        this.moduleName = moduleName;
       this.courseModulePersistence = CourseModulePersistenceSingleton.GetCourseModulePersistence();
    }

    private void SetModuleID(int moduleId){
        this.moduleId = moduleId;
    }

    void AddCourseContent(CourseContent courseContent){
        contentList.add(courseContent);
    }

    void RemoveCourseContent(Integer contentId){

    }

   public int GetCourseModuleId(){
        return moduleId;
    }

    public String GetModuleName(){
        return moduleName;
    }

    public ArrayList<CourseContent> GetCourseContents(){
        return new ArrayList<>(contentList);
    }

    public void Save(){
        int moduleId = courseModulePersistence.Save(this);
        SetModuleID(moduleId);
    }

    public CourseModule Load(int moduleId){
        CourseModule courseModule = courseModulePersistence.Load(moduleId);
        courseModule.SetModuleID(moduleId);
        return courseModule;
    }
}
