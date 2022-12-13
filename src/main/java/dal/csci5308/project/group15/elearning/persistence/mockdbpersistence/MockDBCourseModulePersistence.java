package dal.csci5308.project.group15.elearning.persistence.mockdbpersistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseContentFactory;
import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;
import dal.csci5308.project.group15.elearning.persistence.CourseModulePersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public class MockDBCourseModulePersistence implements CourseModulePersistence {

    public int Save(CourseModule courseModule, String courseId){

        return 1;
    }


    public CourseModule Load(int courseModuleID){
        CourseContentFactory courseContentFactory = new CourseContentFactory();

        return courseContentFactory.CreateCourseModule("module1");
    }

    public ArrayList<CourseModule> GetAllModulesInCourse(String courseId) throws SQLException{

        return new ArrayList<>();
    }
}
