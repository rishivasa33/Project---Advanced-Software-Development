package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.coursepersistence;

import dal.csci5308.project.group15.elearning.factory.FactoryFacade;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContent;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.models.course.courseContent.ICourseContentFactory;
import dal.csci5308.project.group15.elearning.persistence.coursepersistence.coursecontentpersistence.CourseModulePersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockDBCourseModulePersistence implements CourseModulePersistence {

    public int Save(CourseModule courseModule, String courseId){

        return 1;
    }


    public CourseModule Load(int courseModuleID){
        ICourseContentFactory courseContentFactory = FactoryFacade.instance().getCourseContentFactory();

        return courseContentFactory.CreateCourseModule("module1");
    }

    public ArrayList<CourseModule> GetAllModulesInCourse(String courseId) throws SQLException{

        return new ArrayList<>();
    }

    public ArrayList<CourseContent> GetAllContentsInAModule(int courseModuleId) throws SQLException{
        return null;
    }

    public CourseContent LoadCourseContent(int courseContentId){
        return null;
    }
}
