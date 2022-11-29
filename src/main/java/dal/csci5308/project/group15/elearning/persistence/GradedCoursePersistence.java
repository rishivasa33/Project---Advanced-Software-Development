package dal.csci5308.project.group15.elearning.persistence;

import dal.csci5308.project.group15.elearning.models.course.GradedCourse;
import dal.csci5308.project.group15.elearning.models.course.ICourse;
import dal.csci5308.project.group15.elearning.persistence.mockdbpersistence.ICoursePersistence;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class GradedCoursePersistence implements ICoursePersistence {


    public void Save(ICourse iCourse) throws SQLException {
        Save((GradedCourse) iCourse);
    }

    public ICourse Load(ICourse iCourse, int iCourseId){
        return (ICourse) Load((GradedCourse)(iCourse), iCourseId);
    }
   public abstract GradedCourse Load(int course_id) throws SQLException;

    public abstract void Save(GradedCourse course) throws SQLException;

    GradedCourse Load(String course_id) throws SQLException;


    public abstract ArrayList<GradedCourse> GetAllGradedCourses();
}
