package dal.csci5308.project.group15.elearning.models.course;


import dal.csci5308.project.group15.elearning.persistence.UnGradedCoursePersistence;

import java.sql.SQLException;

public class UnGradedCourse implements ICourse {
    private Course course_;


    UnGradedCourse(int course_id, String course_name, String course_description){
        course_ = new Course(course_id, course_name, course_description);
    }

    public void Save(UnGradedCoursePersistence coursePersistence) throws SQLException{
        coursePersistence.Save(this);
    }

   public  ICourse Load(UnGradedCoursePersistence iCoursePersistence, int course_id) throws SQLException
    {
       return iCoursePersistence.Load(course_id);
    }


    public Course GetCourse(){
        return course_;
    }


//    UnGradedCourse Load(UnGradedCoursePersistence course_persistence, int course_id){
//        return course_persistence.Load(course_id);
//    }



    public int GetCourseID(){
        return GetCourse().GetCourseID();
    }
    public String GetCourseName(){
        return course_.GetName();
    }
    public String GetCourseDescription(){
        return course_.GetDescription();
    }
    public boolean IsGradedCourse(){
        return  true;
    }

}
