package dal.csci5308.project.group15.elearning.models.course;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.models.course.courseContent.FileCourseContent;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseModulePersistence;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseModulePersistenceSingleton;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.CoursePersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public class BaseCourse {
    private String course_id_;
    private String course_name_;

    private String course_description_;

    private ArrayList<CourseModule> moduleArrayList;

    private void LoadModulesArrayList() throws SQLException {
        if(moduleArrayList == null){
            CourseModulePersistence courseModulePersistence =  CourseModulePersistenceSingleton.GetCourseModulePersistence();
            moduleArrayList =  courseModulePersistence.GetAllModulesInCourse(GetCourseID());
        }
    }



    public void Save(CoursePersistence course_persistence) throws SQLException {
        course_persistence.Save(this);
        if(moduleArrayList != null){
            for (CourseModule module : moduleArrayList){
                module.Save(GetCourseID());
            }
        }
    }
    BaseCourse Load(CoursePersistence course_persistence, String course_id){
        return course_persistence.Load(course_id);
    }
    public String GetName(){
        return course_name_;
    }


    public String GetDescription(){
        return course_description_;
    }
    BaseCourse(String course_id, String course_name, String course_description){
        course_id_ = course_id;
        course_name_ = course_name;
        course_description_ = course_description;
        moduleArrayList = null;
    }

    BaseCourse (String course_id){
        course_id_ = course_id;
    }

    protected void SetCourseID(String courseId){
        course_id_ = courseId;
    }

    public String GetCourseID(){
        return  course_id_;
    }

    public ArrayList<CourseModule> GetAllModules() throws SQLException {
      LoadModulesArrayList();
      return moduleArrayList;
    }

    public ArrayList<CourseContent> GetAllContentsInAModule(int courseModuleId) throws SQLException {
        LoadModulesArrayList();
        for(CourseModule module : moduleArrayList){
            if(module.GetCourseModuleId().equals(courseModuleId)){
                return module.GetCourseContents();
            }
        }
        throw new SQLException("Module with Id not Found");
    }

    public String GetModuleName(int courseModuleId) throws SQLException {
        LoadModulesArrayList();
        for(CourseModule module : moduleArrayList){
            if(module.GetCourseModuleId().equals(courseModuleId)){
                return module.GetModuleName();
            }
        }
        throw new SQLException("Module with Id not Found");
    }

    public FileCourseContent GetContentFilePath(int courseModuleId, int courseModuleContentId) throws SQLException {
        LoadModulesArrayList();
        for(CourseModule module : moduleArrayList){
            if(module.GetCourseModuleId().equals(courseModuleId)){
                CourseContent courseContent = module.LoadModuleContent(courseModuleContentId);
                if(!courseContent.IsTextContent()){
                    FileCourseContent fileCourseContent = (FileCourseContent) courseContent;
                    return fileCourseContent;
                }
            }
        }
        return null;
    }

}
