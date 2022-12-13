package dal.csci5308.project.group15.elearning.persistence.coursepersistence;

import dal.csci5308.project.group15.elearning.models.course.Course;


import java.sql.SQLException;
import java.util.ArrayList;

public interface GradedCoursePersistence{



    public  ArrayList<Course> GetAllGradedCourses() throws SQLException;

    void Save(Course course) throws SQLException;

    Course Load(String courseId) throws SQLException;




}
