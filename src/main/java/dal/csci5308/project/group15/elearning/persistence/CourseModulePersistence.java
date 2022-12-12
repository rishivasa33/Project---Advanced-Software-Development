package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.courseContent.CourseModule;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CourseModulePersistence {

    int Save(CourseModule courseModule, String courseId) throws SQLException;

    CourseModule Load(int courseModuleID) throws SQLException;

    public ArrayList<CourseModule> GetAllModulesInCourse(String courseId) throws SQLException;


}
