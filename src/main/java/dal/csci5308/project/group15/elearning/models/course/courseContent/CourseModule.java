package dal.csci5308.project.group15.elearning.models.course.courseContent;



import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.persistence.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseModule {
    private String moduleName;

    private Integer moduleId;
    private ArrayList<CourseContent> contentList;

    private CourseModulePersistence courseModulePersistence;

    CourseModule(String moduleName){
        this.moduleName = moduleName;
       this.courseModulePersistence = CourseModulePersistenceSingleton.GetCourseModulePersistence();
    }

    void SetModuleID(int moduleId){
        this.moduleId = moduleId;
    }

    void AddCourseContent(CourseContent courseContent){
        contentList.add(courseContent);
    }

    void RemoveCourseContent(Integer contentId){

    }

   public Integer GetCourseModuleId(){
        return moduleId;
    }

    public String GetModuleName(){
        return moduleName;
    }

    public ArrayList<CourseContent> GetCourseContents() throws SQLException {
        if(contentList == null){
            if(moduleId == null){
                contentList = new ArrayList<>();
            }
            else {
                TextCourseContentPersistence courseContentPersistence = CourseContentPersistenceSingleton.GetTextCourseContentPersistence();
                contentList = courseContentPersistence.LoadAllContentsInModule(moduleId);
            }
        }
        return contentList;
    }

    public void Save(String courseId) throws SQLException {
        int moduleId = courseModulePersistence.Save(this, courseId);
        SetModuleID(moduleId);
        if(contentList != null){
            for(CourseContent courseContent: contentList){
                courseContent.Save(GetCourseModuleId());
            }
        }
    }

    public CourseModule Load(int moduleId) throws SQLException {
        CourseModule courseModule = courseModulePersistence.Load(moduleId);
        courseModule.SetModuleID(moduleId);
        return courseModule;
    }
}
