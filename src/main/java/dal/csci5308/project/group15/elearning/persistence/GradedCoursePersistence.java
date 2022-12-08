package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.GradedCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GradedCoursePersistence{



    public  ArrayList<GradedCourse> GetAllGradedCourses() throws SQLException;

    void Save(GradedCourse gradedCourse) throws SQLException;

    GradedCourse Load(String courseId) throws SQLException;




}
